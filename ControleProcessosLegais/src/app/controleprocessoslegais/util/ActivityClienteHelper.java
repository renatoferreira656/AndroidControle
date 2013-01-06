package app.controleprocessoslegais.util;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.IncluirAlterarClienteActivity;
import app.controleprocessoslegais.activitys.VisualizarClienteActivity;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class ActivityClienteHelper {

	public static void insereValoresEmTelaVisualizar(ClienteWrapper cliente, VisualizarClienteActivity activity) {
		TextView nomeTextView = (TextView) activity.findViewById(R.idVisualizar.nomeTextView);
		TextView rgTextView = (TextView) activity.findViewById(R.idVisualizar.rgTextView);
		TextView orgaoExpedidorTextView = (TextView) activity.findViewById(R.idVisualizar.orgaoExpedidorTextView);
		TextView cpfCnpjTextView = (TextView) activity.findViewById(R.idVisualizar.cpfcnpjTextView);
		TextView ruaTextView = (TextView) activity.findViewById(R.idVisualizar.ruaTextView);
		TextView bairroTextView = (TextView) activity.findViewById(R.idVisualizar.bairroTextView);
		TextView cepTextView = (TextView) activity.findViewById(R.idVisualizar.cepTextView);
		TextView estadoTextView = (TextView) activity.findViewById(R.idVisualizar.estadoTextView);
		TextView numeroTextView = (TextView) activity.findViewById(R.idVisualizar.numeroTextView);
		TextView estadoCivilTextView = (TextView) activity.findViewById(R.idVisualizar.estadoCivilTextView);
		TextView nacionalidadeTextView = (TextView) activity.findViewById(R.idVisualizar.nacionalidadeTextView);
		TextView profissaoTextView = (TextView) activity.findViewById(R.idVisualizar.profissaoTextView);
		TextView maeTextView = (TextView) activity.findViewById(R.idVisualizar.maeTextView);
		TextView paiTextView = (TextView) activity.findViewById(R.idVisualizar.paiTextView);
		TextView ctpsTextView = (TextView) activity.findViewById(R.idVisualizar.ctpsTextView);
		TextView pisPasepTextView = (TextView) activity.findViewById(R.idVisualizar.pisPasepTextView);
		TextView inscricaoEstadualTextView = (TextView) activity.findViewById(R.idVisualizar.inscricaoEstadualTextView);

		nomeTextView.setText(cliente.getNomeCompleto());
		rgTextView.setText(cliente.getRg());
		orgaoExpedidorTextView.setText(cliente.getOrgaoExpedidor());
		cpfCnpjTextView.setText(cliente.getCpf());
		ruaTextView.setText(cliente.getRua());
		bairroTextView.setText(cliente.getBairro());
		cepTextView.setText(cliente.getCep());
		estadoTextView.setText(cliente.getEstado());
		numeroTextView.setText(cliente.getNumero());
		estadoCivilTextView.setText(cliente.getEstadoCivil());
		nacionalidadeTextView.setText(cliente.getNacionalidade());
		profissaoTextView.setText(cliente.getProfissao());
		maeTextView.setText(cliente.getMae());
		paiTextView.setText(cliente.getPai());
		ctpsTextView.setText(cliente.getCtps());
		pisPasepTextView.setText(cliente.getPisPasep());
		inscricaoEstadualTextView.setText(cliente.getInscricaoEstadual());
	}

	public static void insereValoresEmTelaAlterar(ClienteWrapper cliente, IncluirAlterarClienteActivity activity) {
		EditText nomeEditView = (EditText) activity.findViewById(R.idIncluir.nomeEditView);
		EditText rgEditView = (EditText) activity.findViewById(R.idIncluir.rgEditView);
		EditText orgaoExpedidorEditView = (EditText) activity.findViewById(R.idIncluir.orgaoExpedidorEditView);
		EditText cpfCnpjEditView = (EditText) activity.findViewById(R.idIncluir.cpfcnpjEditView);
		EditText ruaEditView = (EditText) activity.findViewById(R.idIncluir.ruaEditView);
		EditText bairroEditView = (EditText) activity.findViewById(R.idIncluir.bairroEditView);
		EditText cepEditView = (EditText) activity.findViewById(R.idIncluir.cepEditView);
		Spinner estadoSpinnerView = (Spinner) activity.findViewById(R.idIncluir.estadoSpinnerView);
		EditText numeroEditView = (EditText) activity.findViewById(R.idIncluir.numeroEditView);
		Spinner estadoCivilSpinnerView = (Spinner) activity.findViewById(R.idIncluir.estadoCivilSpinnerView);
		EditText nacionalidadeEditView = (EditText) activity.findViewById(R.idIncluir.nacionalidadeEditView);
		EditText profissaoEditView = (EditText) activity.findViewById(R.idIncluir.profissaoEditView);
		EditText maeEditView = (EditText) activity.findViewById(R.idIncluir.maeEditView);
		EditText paiEditView = (EditText) activity.findViewById(R.idIncluir.paiEditView);
		EditText ctpsEditView = (EditText) activity.findViewById(R.idIncluir.ctpsEditView);
		EditText pisPasepEditView = (EditText) activity.findViewById(R.idIncluir.pisPasepEditView);
		EditText inscricaoEstadualEditView = (EditText) activity.findViewById(R.idIncluir.inscricaoEstadualEditView);

		nomeEditView.setText(cliente.getNomeCompleto());
		rgEditView.setText(cliente.getRg());
		orgaoExpedidorEditView.setText(cliente.getOrgaoExpedidor());
		cpfCnpjEditView.setText(cliente.getCpf());
		ruaEditView.setText(cliente.getRua());
		bairroEditView.setText(cliente.getBairro());
		cepEditView.setText(cliente.getCep());
		numeroEditView.setText(cliente.getNumero());
		nacionalidadeEditView.setText(cliente.getNacionalidade());
		profissaoEditView.setText(cliente.getProfissao());
		maeEditView.setText(cliente.getMae());
		paiEditView.setText(cliente.getPai());
		ctpsEditView.setText(cliente.getCtps());
		pisPasepEditView.setText(cliente.getPisPasep());
		inscricaoEstadualEditView.setText(cliente.getInscricaoEstadual());
		
		
		for (int i = 0; i < estadoCivilSpinnerView.getCount(); i++) {
			String estadoCivil = String.class.cast(estadoCivilSpinnerView.getItemAtPosition(i));
			if(cliente.getEstadoCivil()!=null && cliente.getEstadoCivil().equals(estadoCivil)){
				estadoCivilSpinnerView.setSelection(i);
				break;
			}
		}

		for (int i = 0; i < estadoSpinnerView.getCount(); i++) {
			String estado = String.class.cast(estadoSpinnerView.getItemAtPosition(i));
			if(cliente.getEstado()!=null && cliente.getEstado().equals(estado)){
				estadoSpinnerView.setSelection(i);
				break;
			}
		}
	}
}
