package app.controleprocessoslegais.activitys;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.util.Tags;

public class CadastroClienteActivity extends Activity {
	Map<Tags,String> mapaSpinner = new HashMap<Tags,String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		// Button botaoSalvar = (Button) findViewById(R.id.botaoSalvar);
		// final ClienteDao dao = new ClienteDao(getApplicationContext());
		// final CadastroClienteActivity activity = this;
		// botaoSalvar.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// ClienteWrapper clienteNovo = Builders
		// .constroiClienteFromViews(activity);
		// dao.addCliente(clienteNovo);
		// }
		// });
	}
}
