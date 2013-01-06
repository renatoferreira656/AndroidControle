package app.controleprocessoslegais.contantes;

import java.util.Map;

import android.app.Activity;
import app.controleprocessoslegais.listener.ClienteMenuListener;

public enum ClienteListagemContextMenu {
	EDITAR("Editar") {
		@Override
		public boolean efetuarAcao(Activity activity, Map<ConstantesTransporte, String> args) {
			return ClienteMenuListener.EDITAR_CLIENTE.inicializarActivity(activity, args);
		}
	},
	REMOVER("Remover") {
		@Override
		public boolean efetuarAcao(Activity activity, Map<ConstantesTransporte, String> args) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	private ClienteListagemContextMenu(String title) {
		this.title = title;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public static ClienteListagemContextMenu valorDe(CharSequence title) {
		for (ClienteListagemContextMenu value : ClienteListagemContextMenu.values()) {
			if (value.getTitle().equals(title)) {
				return value;
			}
		}
		return null;
	}

	public abstract boolean efetuarAcao(Activity activity, Map<ConstantesTransporte, String> args);
}
