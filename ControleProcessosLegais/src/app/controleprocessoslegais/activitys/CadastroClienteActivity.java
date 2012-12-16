package app.controleprocessoslegais.activitys;

import android.app.Activity;
import android.os.Bundle;
import app.controleprocessoslegais.R;

public class CadastroClienteActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
//		Button botaoSalvar = (Button) findViewById(R.id.botaoSalvar);
//		final ClienteDao dao = new ClienteDao(getApplicationContext());
//		final CadastroClienteActivity activity = this;
//		botaoSalvar.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				ClienteWrapper clienteNovo = Builders
//						.constroiClienteFromViews(activity);
//				dao.addCliente(clienteNovo);
//			}
//		});
	}
}
