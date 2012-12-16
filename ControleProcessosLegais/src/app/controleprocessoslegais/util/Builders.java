package app.controleprocessoslegais.util;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.CadastroClienteActivity;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class Builders {

	public static ClienteWrapper constroiClienteFromViews(CadastroClienteActivity activityCliente) {
		List<View> listaCamposObrigatorio = new ArrayList<View>();
		EditText nomeEditView = (EditText) activityCliente.findViewById(R.id.nomeEditView);
		listaCamposObrigatorio.add(nomeEditView);
		EditText rgEditView = (EditText) activityCliente.findViewById(R.id.rgEditView);
		listaCamposObrigatorio.add(rgEditView);
		EditText orgaoExpedidorEditView = (EditText) activityCliente.findViewById(R.id.orgaoExpedidorEditView);
		listaCamposObrigatorio.add(orgaoExpedidorEditView);
		EditText cpfCnpjEditView = (EditText) activityCliente.findViewById(R.id.cpfcnpjEditView);
		listaCamposObrigatorio.add(cpfCnpjEditView);
		EditText ruaEditView = (EditText) activityCliente.findViewById(R.id.ruaEditView);
		listaCamposObrigatorio.add(ruaEditView);
		EditText bairroEditView = (EditText) activityCliente.findViewById(R.id.bairroEditView);
		listaCamposObrigatorio.add(bairroEditView);
		EditText cepEditView = (EditText) activityCliente.findViewById(R.id.cepEditView);
		listaCamposObrigatorio.add(cepEditView);
		Spinner estadoSpinnerView = (Spinner) activityCliente.findViewById(R.id.estadoSpinnerView);
		listaCamposObrigatorio.add(estadoSpinnerView);
		EditText numeroEditView = (EditText) activityCliente.findViewById(R.id.numeroEditView);
		listaCamposObrigatorio.add(numeroEditView);
		Spinner estadoCivilEditView = (Spinner) activityCliente.findViewById(R.id.estadoCivilSpinnerView);
		listaCamposObrigatorio.add(estadoCivilEditView);
		EditText nacionalidadeEditView = (EditText) activityCliente.findViewById(R.id.nacionalidadeEditView);
		listaCamposObrigatorio.add(nacionalidadeEditView);
		EditText profissaoEditView = (EditText) activityCliente.findViewById(R.id.profissaoEditView);
		listaCamposObrigatorio.add(profissaoEditView);

		if (camposObrigatoriosClienteSaoValidos(listaCamposObrigatorio, activityCliente.getResources())) {
			return null;
		}

		EditText maeEditView = (EditText) activityCliente.findViewById(R.id.maeEditView);
		EditText paiEditView = (EditText) activityCliente.findViewById(R.id.paiEditView);
		EditText ctpsEditView = (EditText) activityCliente.findViewById(R.id.ctpsEditView);
		EditText pisPasepEditView = (EditText) activityCliente.findViewById(R.id.pisPasepEditView);
		EditText inscricaoEstadualEditView = (EditText) activityCliente.findViewById(R.id.inscricaoEstadualEditView);

		ClienteWrapper cliente = new ClienteWrapper();
		cliente.setBairro(bairroEditView.getText().toString());
		cliente.setCep(cepEditView.getText().toString());
		cliente.setCnpg(cpfCnpjEditView.getText().toString());
		cliente.setCpf(cpfCnpjEditView.getText().toString());
		cliente.setEstado(estadoSpinnerView.getSelectedItem().toString());
		cliente.setEstadoCivil(estadoCivilEditView.getSelectedItem().toString());
		cliente.setNacionalidade(nacionalidadeEditView.getText().toString());
		cliente.setNomeCompleto(nomeEditView.getText().toString());
		cliente.setNumero(numeroEditView.getText().toString());
		cliente.setOrgaoExpedidor(orgaoExpedidorEditView.getText().toString());
		cliente.setProfissao(profissaoEditView.getText().toString());
		cliente.setRg(rgEditView.getText().toString());
		cliente.setRua(ruaEditView.getText().toString());

		Editable inscricaoEstadual = inscricaoEstadualEditView.getText();
		if (inscricaoEstadual != null) {
			cliente.setInscricaoEstadual(inscricaoEstadual.toString());
		}
		Editable pisPasep = pisPasepEditView.getText();
		if (pisPasep != null) {
			cliente.setPisPasep(pisPasep.toString());
		}

		Editable ctps = ctpsEditView.getText();
		if (ctps != null) {
			cliente.setCtps(ctps.toString());
		}
		Editable pai = paiEditView.getText();

		if (pai != null) {
			cliente.setPai(pai.toString());
		}
		Editable mae = maeEditView.getText();

		if (mae != null) {
			cliente.setMae(mae.toString());
		}

		return cliente;
	}

	private static boolean camposObrigatoriosClienteSaoValidos(List<View> listaCampos, Resources resources) {
		for (View view : listaCampos) {
			if (!Validador.validateNotNull(view, resources.getString(R.string.campo_obrigatorio))) {
				return false;
			}
		}
		return true;
	}
}
