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

	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public ClienteDao(Context context) {
		helper = new DatabaseHelper(context);
		db = helper.getDatabase();
	}

	public ClienteWrapper addCliente(ClienteWrapper cliente) {
		ContentValues values = BuilderCliente.clienteParaContentValues(cliente);
		long id = db.insert("clientes", null, values);
		cliente.setId(id);
		this.buscarListagemFiltrandoPorInicioDoNome("");
		return cliente;
	}

	/**
	 * 
	 * @param nome
	 *            = inicio do nome para buscar na base
	 * @return
	 */
	public Map<SectionItem, List<EntryItem>> buscarListagemFiltrandoPorInicioDoNome(String nome) {
		String[] buscaColunas = new String[] { ConstantesCliente.NOME_COMPLETO.name(), ConstantesCliente.ID.name() };
		String condition = ConstantesCliente.NOME_COMPLETO.name()+  " like '" + nome + "%'";
		String orderBy = ConstantesCliente.NOME_COMPLETO.name()+" ASC";
		String tableName = "clientes";
		Cursor cursor = db.query(tableName, buscaColunas, condition, null, null, null, orderBy);
		if (cursor!=null && cursor.getCount()>0) {
			cursor.moveToFirst();
			Map<SectionItem, List<EntryItem>> entradas = BuilderCliente.constroiMapListagemAPartirCursor(cursor);
			cursor.close();
			return entradas;
		}
		return new TreeMap<SectionItem, List<EntryItem>>();

	}
}
