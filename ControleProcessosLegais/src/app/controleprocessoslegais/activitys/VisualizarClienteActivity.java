package app.controleprocessoslegais.activitys;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.contantes.ConstantesTransporte;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.listener.ClienteMenuListener;
import app.controleprocessoslegais.util.ActivityClienteHelper;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class VisualizarClienteActivity extends Activity {

	private ClienteDao clienteDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_visualizar_cliente);
		this.clienteDao = new ClienteDao(this.getApplicationContext());
		String idCliente = this.getIntent().getExtras().getString(ConstantesTransporte.ID_CLIENTE.name());
		ClienteWrapper cliente = clienteDao.findById(Long.valueOf(idCliente));
		ActivityClienteHelper.insereValoresEmTelaVisualizar(cliente, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.activity_visualizar_cliente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String idCliente = this.getIntent().getExtras().getString(ConstantesTransporte.ID_CLIENTE.name());
		String nomeFiltro = this.getIntent().getExtras().getString(ConstantesTransporte.NOME_CLIENTE_FILTRO.name());
		Map<ConstantesTransporte, String> args = new HashMap<ConstantesTransporte, String>();
		args.put(ConstantesTransporte.ID_CLIENTE, idCliente);
		args.put(ConstantesTransporte.NOME_CLIENTE_FILTRO, nomeFiltro);
		if (item.getTitle().toString().equals(ClienteMenuListener.EDITAR_CLIENTE.name())) {
			ClienteMenuListener.EDITAR_CLIENTE.setTelaRetorno(ClienteMenuListener.VISUALIZAR_CLIENTE);
			return ClienteMenuListener.EDITAR_CLIENTE.inicializarActivity(this, args);
		} else if (item.getTitle().toString().equals(ClienteMenuListener.LISTAGEM_CLIENTE.name())) {
			return ClienteMenuListener.LISTAGEM_CLIENTE.inicializarActivity(this, args);
		} else if (item.getTitle().toString().equals(ClienteMenuListener.EXCLUIR_CLIENTE.name())) {
			return ClienteMenuListener.EXCLUIR_CLIENTE.inicializarActivity(this, args);
		}
		return false;
	}
}
