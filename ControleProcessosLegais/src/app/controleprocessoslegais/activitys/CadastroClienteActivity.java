package app.controleprocessoslegais.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.builders.BuilderCliente;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class CadastroClienteActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		this.inicializaBotaoSalvar();
		this.incializaBotaoCancelar();
	}

	private final void inicializaBotaoSalvar() {
		Button botaoSalvar = (Button) findViewById(R.idIncluir.botaoSalvar);
		final ClienteDao dao = new ClienteDao(getApplicationContext());
		botaoSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ClienteWrapper clienteNovo = BuilderCliente.constroiClienteAPartirViews(CadastroClienteActivity.this);
				if (clienteNovo != null) {
					dao.addCliente(clienteNovo);
					Intent intent = new Intent(CadastroClienteActivity.this, ListagemClienteActivity.class);
					intent.putExtra("filtroNome", getIntent().getExtras().getString("filtroNome"));
					startActivity(intent);
					finish();
				}
			}
		});
	}

	private final void incializaBotaoCancelar() {
		Button botaoSalvar = (Button) findViewById(R.idIncluir.botaoCancelar);
		botaoSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(CadastroClienteActivity.this, ListagemClienteActivity.class);
				intent.putExtra("filtroNome", getIntent().getExtras().getString("filtroNome"));
				startActivity(intent);
				finish();
			}
		});

	}
}
