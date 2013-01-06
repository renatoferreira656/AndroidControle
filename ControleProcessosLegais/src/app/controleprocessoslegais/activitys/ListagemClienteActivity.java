package app.controleprocessoslegais.activitys;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.sectionlistview.EntryAdapter;
import app.controleprocessoslegais.activitys.sectionlistview.EntryItem;
import app.controleprocessoslegais.activitys.sectionlistview.Item;
import app.controleprocessoslegais.activitys.sectionlistview.SectionItem;
import app.controleprocessoslegais.builders.BuilderCliente;
import app.controleprocessoslegais.contantes.ClienteListagemContextMenu;
import app.controleprocessoslegais.contantes.ConstantesTransporte;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.listener.ClienteMenuListener;

public class ListagemClienteActivity extends Activity implements OnItemClickListener, OnItemLongClickListener,
		OnClickListener {

	private EntryAdapter entryAdapter;
	private ClienteDao clienteDao;
	private ListView listView;
	private EditText searchEdit;
	private String idCliente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_listagem_cliente);
		this.clienteDao = new ClienteDao(this.getApplicationContext());
		this.defineEditProcura();
		this.defineListagem();
		this.defineBotaoAdicionar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.activity_listagem_cliente, menu);
		return true;
	}

	public void onItemClick(AdapterView<?> entry, View view, int position, long id) {
		Item entryItem = EntryAdapter.class.cast(entry.getAdapter()).getItem(position);
		String search = searchEdit.getText() == null ? "" : searchEdit.getText().toString();
		Map<ConstantesTransporte, String> args = new HashMap<ConstantesTransporte, String>();
		args.put(ConstantesTransporte.NOME_CLIENTE_FILTRO, search);
		args.put(ConstantesTransporte.ID_CLIENTE, EntryItem.class.cast(entryItem).getId());
		ClienteMenuListener.VISUALIZAR_CLIENTE.inicializarActivity(this, args);
	}

	public boolean onItemLongClick(AdapterView<?> entry, View view, int position, long id) {
		Item entryItem = EntryAdapter.class.cast(entry.getAdapter()).getItem(position);
		this.idCliente = EntryItem.class.cast(entryItem).getId();
		return false;
	}

	public void onClick(View v) {
		EditText searchEdit = EditText.class.cast(this.findViewById(R.idListagem.searchEdit));
		String search = searchEdit.getText() == null ? "" : searchEdit.getText().toString();
		ClienteMenuListener.INCLUIR_CLIENTE.inicializarActivity(this,
				Collections.singletonMap(ConstantesTransporte.NOME_CLIENTE_FILTRO, search));
	}

	private void defineEditProcura() {
		this.searchEdit = EditText.class.cast(this.findViewById(R.idListagem.searchEdit));
		searchEdit.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void afterTextChanged(Editable s) {
				String buscaPor = (s == null) ? "" : s.toString();
				atualizaListagem(buscaPor);
			}
		});
	}

	private void defineBotaoAdicionar() {
		Button addButton = Button.class.cast(this.findViewById(R.idListagem.botaoAdicionar));
		addButton.setOnClickListener(this);
	}

	private void defineListagem() {
		this.listView = ListView.class.cast(this.findViewById(R.idListagem.listView1));
		String search = atualizaIntent();
		this.atualizaListagem(search);
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
		this.registerForContextMenu(this.listView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Context Menu");
		menu.add(ClienteListagemContextMenu.EDITAR.getTitle());
		menu.add(ClienteListagemContextMenu.REMOVER.getTitle());
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Map<ConstantesTransporte, String> args = new HashMap<ConstantesTransporte, String>();
		String search = searchEdit.getText() == null ? "" : searchEdit.getText().toString();
		args.put(ConstantesTransporte.NOME_CLIENTE_FILTRO, search);
		args.put(ConstantesTransporte.ID_CLIENTE, this.idCliente);
		ClienteListagemContextMenu.valorDe(item.getTitle()).efetuarAcao(this, args);
		return true;
	}

	private String atualizaIntent() {
		Bundle extras = this.getIntent().getExtras();
		String search = "";
		if (extras != null) {
			search = extras.getString(ConstantesTransporte.NOME_CLIENTE_FILTRO.name());
			if (search == null) {
				search = "";
			}
		}
		this.searchEdit.setText(search);
		return search;
	}

	private void atualizaListagem(String filtroPor) {
		Map<SectionItem, List<EntryItem>> mapaEntradas = this.clienteDao
				.buscarListagemFiltrandoPorInicioDoNome(filtroPor);
		List<Item> items = BuilderCliente.converteMapEmLista(mapaEntradas);
		this.entryAdapter = new EntryAdapter(this.getApplicationContext(), items, R.layout.list_item_entry,
				R.layout.list_item_section, R.id.list_item_section_text, R.id.list_item_entry_title);
		this.listView.setAdapter(this.entryAdapter);
		this.listView.invalidateViews();
	}

}
