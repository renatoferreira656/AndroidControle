package app.controleprocessoslegais.contantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.database.Cursor;

public enum ConstantesCliente {
	ID, NOME_COMPLETO, RG, CPF, ORGAO_EXPEDIDOR, RUA, NUMERO, CEP, BAIRRO, ESTADO, NACIONALIDADE, ESTADO_CIVIL, PROFISSAO, CNPJ, CTPS, PIS_PASEP, INSCRICAO_ESTADUAL, PAI, MAE;

	public String getResultByCursor(Cursor cursor) {
		int idx = cursor.getColumnIndex(this.name().toLowerCase(Locale.ENGLISH));
		return cursor.getString(idx);
	}

	
	public static String[] getArrayStringValues() {
		List<String> arrayString = new ArrayList<String>();
		for (ConstantesCliente nome : ConstantesCliente.values()) {
			arrayString.add(nome.name());
		}
		return arrayString.toArray(new String[arrayString.size()]);
	}
}