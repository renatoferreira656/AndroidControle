package app.controleprocessoslegais.contantes;

import android.database.Cursor;

public enum ConstantesCliente {
	ID, NOME_COMPLETO, RG, CPF, ORGAO_EXPEDIDOR, RUA, NUMERO, CEP, BAIRRO, ESTADO, NACIONALIDADE, ESTADO_CIVIL, PROFISSAO, CNPJ, CTPS, PIS_PASEP, INSCRICAO_ESTADUAL, PAI, MAE;

	public String getResultByCursor(Cursor cursor){
		int idx = cursor.getColumnIndex(this.name().toLowerCase());
		return cursor.getString(idx);
	}
}