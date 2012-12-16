package app.controleprocessoslegais.util;

import android.widget.EditText;
import android.widget.Spinner;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.CadastroClienteActivity;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class Builders {

	public static ClienteWrapper constroiClienteFromViews(CadastroClienteActivity activityCliente) {
		EditText bairroEditView = (EditText) activityCliente.findViewById(R.id.bairroEditView);
		EditText cepEditView = (EditText) activityCliente.findViewById(R.id.cepEditView);
		EditText nomeEditView = (EditText) activityCliente.findViewById(R.id.nomeEditView);
		EditText rgEditView = (EditText) activityCliente.findViewById(R.id.rgEditView);
		EditText cpfCnpjEditView = (EditText) activityCliente.findViewById(R.id.cpfcnpjEditView);
		EditText orgaoExpedidorEditView = (EditText) activityCliente.findViewById(R.id.orgaoExpedidorEditView);
		EditText ruaEditView = (EditText) activityCliente.findViewById(R.id.ruaEditView);
		EditText numeroEditView = (EditText) activityCliente.findViewById(R.id.numeroEditView);
		Spinner estadoCivilEditView = (Spinner) activityCliente.findViewById(R.id.estadoCivilSpinnerView);
		EditText nacionalidadeEditView = (EditText) activityCliente.findViewById(R.id.nacionalidadeEditView);
		EditText profissaoEditView = (EditText) activityCliente.findViewById(R.id.profissaoEditView);
		EditText maeEditView = (EditText) activityCliente.findViewById(R.id.maeEditView);
		EditText paiEditView = (EditText) activityCliente.findViewById(R.id.paiEditView);
		EditText ctpsEditView = (EditText) activityCliente.findViewById(R.id.ctpsEditView);
		EditText pisPasepEditView = (EditText) activityCliente.findViewById(R.id.pisPasepEditView);
		EditText inscricaoEstadualEditView = (EditText) activityCliente.findViewById(R.id.inscricaoEstadualEditView);
		Spinner estadoSpinnerView = (Spinner) activityCliente.findViewById(R.id.estadoSpinnerView);

		ClienteWrapper cliente = new ClienteWrapper();
		cliente.setBairro(bairroEditView.getText().toString());
		cliente.setCep(cepEditView.getText().toString());
		cliente.setCnpg(cpfCnpjEditView.getText().toString());
		cliente.setCpf(cpfCnpjEditView.getText().toString());
		cliente.setCtps(ctpsEditView.getText().toString());
		cliente.setEstado(estadoSpinnerView.getSelectedItem().toString());
		cliente.setEstadoCivil(estadoCivilEditView.getSelectedItem().toString());
		cliente.setInscricaoEstadual(inscricaoEstadualEditView.getText().toString());
		cliente.setNacionalidade(nacionalidadeEditView.getText().toString());
		cliente.setNomeCompleto(nomeEditView.getText().toString());
		cliente.setNumero(numeroEditView.getText().toString());
		cliente.setOrgaoExpedidor(orgaoExpedidorEditView.getText().toString());
		cliente.setPisPasep(pisPasepEditView.getText().toString());
		cliente.setProfissao(profissaoEditView.getText().toString());
		cliente.setRg(rgEditView.getText().toString());
		cliente.setRua(ruaEditView.getText().toString());
		cliente.setPai(paiEditView.getText().toString());
		cliente.setMae(maeEditView.getText().toString());

		return cliente;
	}
}
