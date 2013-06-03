package app.controleprocessoslegais.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.IncluirAlterarClienteActivity;
import app.controleprocessoslegais.activitys.sectionlistview.EntryItem;
import app.controleprocessoslegais.activitys.sectionlistview.Item;
import app.controleprocessoslegais.activitys.sectionlistview.SectionItem;
import app.controleprocessoslegais.contantes.ConstantesCliente;
import app.controleprocessoslegais.util.Validador;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class BuilderCliente {

	public static ClienteWrapper constroiClienteAPartirViews(IncluirAlterarClienteActivity activityCliente) {
		List<View> listaCamposObrigatorio = new ArrayList<View>();
		EditText nomeEditView = (EditText) activityCliente.findViewById(R.idIncluir.nomeEditView);
		listaCamposObrigatorio.add(nomeEditView);
		EditText rgEditView = (EditText) activityCliente.findViewById(R.idIncluir.rgEditView);
		listaCamposObrigatorio.add(rgEditView);
		EditText orgaoExpedidorEditView = (EditText) activityCliente.findViewById(R.idIncluir.orgaoExpedidorEditView);
		listaCamposObrigatorio.add(orgaoExpedidorEditView);
		EditText cpfCnpjEditView = (EditText) activityCliente.findViewById(R.idIncluir.cpfcnpjEditView);
		listaCamposObrigatorio.add(cpfCnpjEditView);
		EditText ruaEditView = (EditText) activityCliente.findViewById(R.idIncluir.ruaEditView);
		listaCamposObrigatorio.add(ruaEditView);
		EditText bairroEditView = (EditText) activityCliente.findViewById(R.idIncluir.bairroEditView);
		listaCamposObrigatorio.add(bairroEditView);
		EditText cepEditView = (EditText) activityCliente.findViewById(R.idIncluir.cepEditView);
		listaCamposObrigatorio.add(cepEditView);
		Spinner estadoSpinnerView = (Spinner) activityCliente.findViewById(R.idIncluir.estadoSpinnerView);
		listaCamposObrigatorio.add(estadoSpinnerView);
		EditText numeroEditView = (EditText) activityCliente.findViewById(R.idIncluir.numeroEditView);
		listaCamposObrigatorio.add(numeroEditView);
		Spinner estadoCivilEditView = (Spinner) activityCliente.findViewById(R.idIncluir.estadoCivilSpinnerView);
		listaCamposObrigatorio.add(estadoCivilEditView);
		EditText nacionalidadeEditView = (EditText) activityCliente.findViewById(R.idIncluir.nacionalidadeEditView);
		listaCamposObrigatorio.add(nacionalidadeEditView);
		EditText profissaoEditView = (EditText) activityCliente.findViewById(R.idIncluir.profissaoEditView);
		listaCamposObrigatorio.add(profissaoEditView);

		// if (camposObrigatoriosClienteSaoValidos(listaCamposObrigatorio,
		// activityCliente.getResources())) {
		// return null;
		// }

		EditText maeEditView = (EditText) activityCliente.findViewById(R.idIncluir.maeEditView);
		EditText paiEditView = (EditText) activityCliente.findViewById(R.idIncluir.paiEditView);
		EditText ctpsEditView = (EditText) activityCliente.findViewById(R.idIncluir.ctpsEditView);
		EditText pisPasepEditView = (EditText) activityCliente.findViewById(R.idIncluir.pisPasepEditView);
		EditText inscricaoEstadualEditView = (EditText) activityCliente.findViewById(R.idIncluir.inscricaoEstadualEditView);

		ClienteWrapper cliente = new ClienteWrapper();
		cliente.setBairro(bairroEditView.getText().toString());
		cliente.setCep(cepEditView.getText().toString());
		cliente.setCnpj(cpfCnpjEditView.getText().toString());
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

	public static ClienteWrapper constroiClienteAPartirCursor(Cursor cursor) {
		ClienteWrapper cliente = new ClienteWrapper();
		cliente.setBairro(ConstantesCliente.BAIRRO.getResultByCursor(cursor));
		cliente.setCep(ConstantesCliente.CEP.getResultByCursor(cursor));
		cliente.setCnpj(ConstantesCliente.CNPJ.getResultByCursor(cursor));
		cliente.setCpf(ConstantesCliente.CPF.getResultByCursor(cursor));
		cliente.setEstado(ConstantesCliente.ESTADO.getResultByCursor(cursor));
		cliente.setEstadoCivil(ConstantesCliente.ESTADO_CIVIL.getResultByCursor(cursor));
		cliente.setNacionalidade(ConstantesCliente.NACIONALIDADE.getResultByCursor(cursor));
		cliente.setNomeCompleto(ConstantesCliente.NOME_COMPLETO.getResultByCursor(cursor));
		cliente.setNumero(ConstantesCliente.NUMERO.getResultByCursor(cursor));
		cliente.setOrgaoExpedidor(ConstantesCliente.ORGAO_EXPEDIDOR.getResultByCursor(cursor));
		cliente.setProfissao(ConstantesCliente.PROFISSAO.getResultByCursor(cursor));
		cliente.setRg(ConstantesCliente.RG.getResultByCursor(cursor));
		cliente.setRua(ConstantesCliente.RUA.getResultByCursor(cursor));
		cliente.setInscricaoEstadual(ConstantesCliente.INSCRICAO_ESTADUAL.getResultByCursor(cursor));
		cliente.setPisPasep(ConstantesCliente.PIS_PASEP.getResultByCursor(cursor));
		cliente.setCtps(ConstantesCliente.CTPS.getResultByCursor(cursor));
		cliente.setPai(ConstantesCliente.PAI.getResultByCursor(cursor));
		cliente.setMae(ConstantesCliente.MAE.getResultByCursor(cursor));
		return cliente;
	}

	public static Map<SectionItem, List<EntryItem>> constroiMapListagemAPartirCursor(Cursor cursor) {
		Map<SectionItem, List<EntryItem>> mapaEntradas = new TreeMap<SectionItem, List<EntryItem>>();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				String nome = ConstantesCliente.NOME_COMPLETO.getResultByCursor(cursor);
				if (nome != null && !nome.equals("")) {
					String letra = nome.substring(0, 1);
					SectionItem section = new SectionItem(letra);
					EntryItem item = new EntryItem(nome, ConstantesCliente.ID.getResultByCursor(cursor));
					List<EntryItem> listaEntradas = mapaEntradas.get(section);
					if (listaEntradas != null) {
						listaEntradas.add(item);
					} else {
						listaEntradas = new ArrayList<EntryItem>();
						listaEntradas.add(item);
						mapaEntradas.put(section, listaEntradas);
					}
				}
				cursor.moveToNext();
			}
			cursor.close();
		}
		return mapaEntradas;
	}

	public static ContentValues clienteParaContentValues(ClienteWrapper cliente) {
		ContentValues values = new ContentValues();
		String nomeCompleto = cliente.getNomeCompleto();
		if (nomeCompleto != null && !nomeCompleto.equals("")) {
			values.put(ConstantesCliente.NOME_COMPLETO.name(), nomeCompleto);
		}
		String rg = cliente.getRg();
		if (rg != null && !rg.equals("")) {
			values.put(ConstantesCliente.RG.name(), rg);
		}
		String cpf = cliente.getCpf();
		if (cpf != null && !cpf.equals("")) {
			values.put(ConstantesCliente.CPF.name(), cpf);
		}

		String orgaoExpedidor = cliente.getOrgaoExpedidor();
		if (orgaoExpedidor != null && !orgaoExpedidor.equals("")) {
			values.put(ConstantesCliente.ORGAO_EXPEDIDOR.name(), orgaoExpedidor);
		}
		String rua = cliente.getRua();
		if (rua != null && !rua.equals("")) {
			values.put(ConstantesCliente.RUA.name(), rua);
		}
		String numero = cliente.getNumero();
		if (numero != null && !numero.equals("")) {
			values.put(ConstantesCliente.NUMERO.name(), numero);
		}
		String cep = cliente.getCep();
		if (cep != null && !cep.equals("")) {
			values.put(ConstantesCliente.CEP.name(), cep);
		}
		String bairro = cliente.getBairro();
		if (bairro != null && !bairro.equals("")) {
			values.put(ConstantesCliente.BAIRRO.name(), bairro);
		}
		String estado = cliente.getEstado();
		if (estado != null && !estado.equals("")) {
			values.put(ConstantesCliente.ESTADO.name(), estado);
		}
		String nacionalidade = cliente.getNacionalidade();
		if (nacionalidade != null && !nacionalidade.equals("")) {
			values.put(ConstantesCliente.NACIONALIDADE.name(), nacionalidade);
		}
		String estadoCivil = cliente.getEstadoCivil();
		if (estadoCivil != null && !estadoCivil.equals("")) {
			values.put(ConstantesCliente.ESTADO_CIVIL.name(), estadoCivil);
		}
		String profissao = cliente.getProfissao();
		if (profissao != null && !profissao.equals("")) {
			values.put(ConstantesCliente.PROFISSAO.name(), profissao);
		}
		String cnpg = cliente.getCnpg();
		if (cnpg != null && !cnpg.equals("")) {
			values.put(ConstantesCliente.CNPJ.name(), cnpg);
		}
		String ctps = cliente.getCtps();
		if (ctps != null && !ctps.equals("")) {
			values.put(ConstantesCliente.CTPS.name(), ctps);
		}
		String pisPasep = cliente.getPisPasep();
		if (pisPasep != null && !pisPasep.equals("")) {
			values.put(ConstantesCliente.PIS_PASEP.name(), pisPasep);
		}
		String inscricaoEstadual = cliente.getInscricaoEstadual();
		if (inscricaoEstadual != null && !inscricaoEstadual.equals("")) {
			values.put(ConstantesCliente.INSCRICAO_ESTADUAL.name(), inscricaoEstadual);
		}
		String pai = cliente.getPai();
		if (pai != null && !pai.equals("")) {
			values.put(ConstantesCliente.PAI.name(), pai);
		}
		String mae = cliente.getMae();
		if (mae != null && !mae.equals("")) {
			values.put(ConstantesCliente.MAE.name(), mae);
		}
		return values;
	}

	public static List<Item> converteMapEmLista(Map<SectionItem, List<EntryItem>> mapaEntradas) {
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
