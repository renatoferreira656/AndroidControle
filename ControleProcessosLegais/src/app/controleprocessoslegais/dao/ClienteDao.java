package app.controleprocessoslegais.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.controleprocessoslegais.activitys.sectionlistview.EntryItem;
import app.controleprocessoslegais.activitys.sectionlistview.SectionItem;
import app.controleprocessoslegais.builders.BuilderCliente;
import app.controleprocessoslegais.contantes.ConstantesCliente;
import app.controleprocessoslegais.util.DatabaseHelper;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public class ClienteDao {

	private static final String TABLE_CLIENTES = "clientes";
	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public ClienteDao(Context context) {
		helper = new DatabaseHelper(context);
		db = helper.getDatabase();
	}

	public ClienteWrapper addCliente(ClienteWrapper cliente) {
		ContentValues values = BuilderCliente.clienteParaContentValues(cliente);
		long id = db.insert(TABLE_CLIENTES, null, values);
		cliente.setId(id);
		return cliente;
	}

	public ClienteWrapper modifyCliente(ClienteWrapper cliente) {
		ContentValues values = BuilderCliente.clienteParaContentValues(cliente);
		String whereClause = "id=" + cliente.getId();
		db.update(TABLE_CLIENTES, values, whereClause, null);
		return cliente;
	}

	public void removeCliente(ClienteWrapper cliente) {
		String whereClause = "id=" + cliente.getId();
		db.delete(TABLE_CLIENTES, whereClause, null);
	}

	/**
	 * 
	 * @param nome
	 *            = inicio do nome para buscar na base
	 * @return
	 */
	public Map<SectionItem, List<EntryItem>> buscarListagemFiltrandoPorInicioDoNome(String nome) {
		String[] buscaColunas = new String[] { ConstantesCliente.NOME_COMPLETO.name(), ConstantesCliente.ID.name() };
		String[] parametros = new String[] { nome + "%" };
		String condition = ConstantesCliente.NOME_COMPLETO.name() + " like ?";
		String orderBy = ConstantesCliente.NOME_COMPLETO.name() + " ASC";
		Cursor cursor = db.query(TABLE_CLIENTES, buscaColunas, condition, parametros, null, null, orderBy);
		return BuilderCliente.constroiMapListagemAPartirCursor(cursor);
	}

	public ClienteWrapper findById(Long id) {
		String[] buscaColunas = ConstantesCliente.getArrayStringValues();
		String condition = ConstantesCliente.ID.name() + " = " + id + "";
		String orderBy = ConstantesCliente.ID.name() + " ASC";
		String tableName = TABLE_CLIENTES;
		Cursor cursor = db.query(tableName, buscaColunas, condition, null, null, null, orderBy);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			ClienteWrapper cliente = BuilderCliente.constroiClienteAPartirCursor(cursor);
			cursor.close();
			cliente.setId(id);
			return cliente;
		}
		return null;

	}
}
