package app.controleprocessoslegais.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static String DBPATH = "/data/data/app.controleprocessoslegais/databases/";

	private static String DBNAME = "bancodados.sqlite";

	private Context context;

	public DatabaseHelper(Context context) {
		super(context, DBNAME, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(createDataBase(db));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	private boolean checkDataBase() {

		SQLiteDatabase db = null;

		try {
			String path = DBPATH + DBNAME;
			db = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READONLY);
			db.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		return db != null;
	}

	private String createDataBase(SQLiteDatabase db) throws IOException{

		boolean exists = checkDataBase();
		String sql = "";
		if (!exists) {
				InputStream dbInputStream = context.getAssets().open(DBNAME);

				byte[] buffer = new byte[1024];
				int length;
				
				length = dbInputStream.read(buffer);
				while ((length) > 0) {
					sql += new String(buffer);
					length = dbInputStream.read(buffer);
				}

				dbInputStream.close();

		}
		return sql;
	}


	public SQLiteDatabase getDatabase() {

		try {
			String path = DBPATH + DBNAME;
			return SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
			return getWritableDatabase();
		}

	}
}
