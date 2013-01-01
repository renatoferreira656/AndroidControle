package app.controleprocessoslegais.activitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.sectionlistview.EntryAdapter;
import app.controleprocessoslegais.activitys.sectionlistview.EntryItem;
import app.controleprocessoslegais.activitys.sectionlistview.Item;
import app.controleprocessoslegais.activitys.sectionlistview.SectionItem;
import app.controleprocessoslegais.dao.ClienteDao;

public class ListagemClienteActivity extends Activity implements OnItemClickListener, OnItemLongClickListener,
		OnClickListener {

	private EntryAdapter entryAdapter;
	private ClienteDao clienteDao;
	private ListView listView;
	private EditText searchEdit;

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

	public void onItemClick(AdapterView<?> entryAdapter, View view, int position, long id) {
		Toast.makeText(this.getApplicationContext(), "Cliquei" + position, Toast.LENGTH_LONG).show();
	}

	public boolean onItemLongClick(AdapterView<?> entryAdapter, View view, int position, long id) {
		Toast.makeText(this.getApplicationContext(), "Segurei pa carai" + position, Toast.LENGTH_LONG).show();
		return false;
	}

	public void onClick(View v) {
		Intent intent = new Intent(ListagemClienteActivity.this, CadastroClienteActivity.class);
		EditText searchEdit = EditText.class.cast(this.findViewById(R.idListagem.searchEdit));
		String search = searchEdit.getText() == null ? "" : searchEdit.getText().toString();
		intent.putExtra("filtroNome", search);
		startActivity(intent);
		finish();
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
	}

	private String atualizaIntent() {
		Bundle extras = this.getIntent().getExtras();
		String search = "";
		if(extras!=null){
			search = extras.getString("filtroNome");
			if(search == null){
				search = "";
			}
		}
		this.searchEdit.setText(search);
		return search;
	}

	private void atualizaListagem(String filtroPor) {
		Map<SectionItem, List<EntryItem>> mapaEntradas = this.clienteDao
				.buscarListagemFiltrandoPorInicioDoNome(filtroPor);
		List<Item> items = this.converteMapEmLista(mapaEntradas);
		this.entryAdapter = new EntryAdapter(this.getApplicationContext(), items, R.layout.list_item_entry,
				R.layout.list_item_section, R.id.list_item_section_text, R.id.list_item_entry_title);
		this.listView.setAdapter(this.entryAdapter);
		this.listView.invalidateViews();
	}

	private List<Item> converteMapEmLista(Map<SectionItem, List<EntryItem>> mapaEntradas) {
		List<Item> listaEntradas = new ArrayList<Item>();
		for (Item secao : mapaEntradas.keySet()) {
			listaEntradas.add(secao);
			for (Item entradas : mapaEntradas.get(secao)) {
				listaEntradas.add(entradas);
			}
		}
		return listaEntradas;
	}
}
