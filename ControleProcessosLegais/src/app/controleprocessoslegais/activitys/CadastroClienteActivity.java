package app.controleprocessoslegais.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.util.Builders;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class CadastroClienteActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		inicializaBotaoSalvar();
	}

	private final void inicializaBotaoSalvar() {
		Button botaoSalvar = (Button) findViewById(R.id.botaoSalvar);
		final ClienteDao dao = new ClienteDao(getApplicationContext());
		final CadastroClienteActivity activity = this;
		botaoSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ClienteWrapper clienteNovo = Builders.constroiClienteFromViews(activity);
				if (clienteNovo != null) {
					dao.addCliente(clienteNovo);
				}
			}
		});
	}
}
