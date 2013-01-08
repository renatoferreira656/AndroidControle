package app.controleprocessoslegais.activitys;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.builders.BuilderCliente;
import app.controleprocessoslegais.contantes.ConstantesTransporte;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.listener.ClienteMenuListener;
import app.controleprocessoslegais.util.ActivityClienteHelper;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class IncluirAlterarClienteActivity extends Activity {

	private ClienteDao clienteDao;
	private String idCliente;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		this.idCliente = null;
		this.clienteDao = new ClienteDao(getApplicationContext());
		this.atualizarDadosEmTela();
		this.inicializaBotaoSalvar();
		
		this.incializaBotaoCancelar();
	}

	private final void inicializaBotaoSalvar() {
		Button botaoSalvar = (Button) findViewById(R.idIncluir.botaoSalvar);

		botaoSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (idCliente == null) {
					ClienteWrapper cliente = BuilderCliente
							.constroiClienteAPartirViews(IncluirAlterarClienteActivity.this);
					clienteDao.addCliente(cliente);
				} else {
					ClienteWrapper cliente = BuilderCliente
							.constroiClienteAPartirViews(IncluirAlterarClienteActivity.this);
					cliente.setId(Long.valueOf(idCliente));
					clienteDao.modifyCliente(cliente);
				}
				botaoCancelar();
			}
		});
	}

	private final void incializaBotaoCancelar() {
		Button botaoSalvar = (Button) findViewById(R.idIncluir.botaoCancelar);
		botaoSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				botaoCancelar();
			}
		});
	}

	private boolean botaoCancelar() {
		ClienteMenuListener telaRetorno = (ClienteMenuListener) this.getIntent().getExtras()
				.get(ConstantesTransporte.TELA_RETORNO.name());
		Map<ConstantesTransporte, String> args = new HashMap<ConstantesTransporte, String>();
		String filtro = getIntent().getExtras().getString(ConstantesTransporte.NOME_CLIENTE_FILTRO.name());
		args.put(ConstantesTransporte.NOME_CLIENTE_FILTRO, filtro);
		if (this.idCliente == null) {
			return telaRetorno.inicializarActivity(this, args);
		} else {
			args.put(ConstantesTransporte.ID_CLIENTE, idCliente);
			return telaRetorno.inicializarActivity(this, args);
		}
	}

	private void atualizarDadosEmTela() {
		this.idCliente = this.getIntent().getExtras().getString(ConstantesTransporte.ID_CLIENTE.name());
		if (this.idCliente != null) {
			ClienteWrapper cliente = this.clienteDao.findById(Long.valueOf(idCliente));
			ActivityClienteHelper.insereValoresEmTelaAlterar(cliente, this);
		}
	}
}
