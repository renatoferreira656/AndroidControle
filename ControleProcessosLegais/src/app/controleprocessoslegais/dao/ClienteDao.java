package app.controleprocessoslegais.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.controleprocessoslegais.util.DatabaseHelper;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class ClienteDao {

	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public ClienteDao(Context context) {
		helper = new DatabaseHelper(context);
		db = helper.getDatabase();
	}

	public ClienteWrapper addCliente(ClienteWrapper cliente) {
		ContentValues values = criaCliente(cliente);
		long id = db.insert("clientes", null, values);
		cliente.setId(id);
		return cliente;
	}

	/**
	 * Filtrando as linguagens cadastradas no banco. Este método será
	 * utilizado ao pressionar o botão de busca.
	 */
	public List<JSONObject> filterByNome(String nome) {
		List<JSONObject> result = new ArrayList<JSONObject>();
		Cursor cursor = db.query("linguagens", new String[] { "nome",
				"descricao" }, "nome like '%" + nome + "%'", null, null, null,
				"nome ASC" /* ordenando pelo nome */);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.add(readRow(cursor));

			cursor.moveToNext();
		}

		cursor.close();
		return result;
	}

	/**
	 * Recuperando todas a linguagens cadastradas no nosso banco de dados.
	 * Iremos retorna-los em List<JSONObject>, pois é o formato que o nosso
	 * adapter espera.
	 */
	public List<JSONObject> allLinguagens() {
		List<JSONObject> result = new ArrayList<JSONObject>();

		// Iremos buscar todas as linguagens cadastradas no banco
		// As colunas que iremos selecionar serão nome e descricao
		// O objeto de retorno contém a referencias das linhas retornadas
		Cursor cursor = db.query("linguagens", new String[] { "nome",
				"descricao" }, null, /*
									 * buscaremos todas, nao precisamos de
									 * nenhuma condicao
									 */
				null, null, null, "nome ASC" /* ordenando pelo nome */);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.add(readRow(cursor));

			cursor.moveToNext();
		}

		cursor.close();
		return result;
	}

	private JSONObject readRow(Cursor cursor) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("nome", cursor.getString(0));
			obj.put("descricao", cursor.getString(1));
		} catch (JSONException e) {
		}

		return obj;
	}
	private ContentValues criaCliente(ClienteWrapper cliente) {
		ContentValues values = new ContentValues();
		values.put("nome_completo",cliente.getNomeCompleto());
		values.put("rg",cliente.getRg());
		values.put("cpf",cliente.getCpf());
		values.put("orgao_expedidor",cliente.getOrgaoExpedidor());
		values.put("rua",cliente.getRua());
		values.put("numero",cliente.getNumero());
		values.put("cep",cliente.getCep());
		values.put("bairro",cliente.getBairro());
		values.put("estado",cliente.getEstado());
		values.put("nacionalidade",cliente.getNacionalidade());
		values.put("estado_civil",cliente.getEstadoCivil());
		values.put("profissao",cliente.getProfissao());
		values.put("cnpg",cliente.getCnpg());
		values.put("ctps",cliente.getCtps());
		values.put("pis_pasep",cliente.getPisPasep());
		values.put("inscricao_estadual",cliente.getInscricaoEstadual());
		values.put("pai",cliente.getPai());
		values.put("mae",cliente.getMae());
		return values;
	}
}
