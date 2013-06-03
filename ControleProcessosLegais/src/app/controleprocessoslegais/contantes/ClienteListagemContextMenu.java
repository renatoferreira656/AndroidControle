package app.controleprocessoslegais.contantes;

import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.ListagemClienteActivity;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.listener.ClienteMenuListener;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public enum ClienteListagemContextMenu {
	EDITAR("Editar") {
		@Override
		public boolean efetuarAcao(ListagemClienteActivity activity, Map<ConstantesTransporte, String> args) {
//			ClienteMenuListener.EDITAR_CLIENTE.setTelaRetorno(ClienteMenuListener.LISTAGEM_CLIENTE);
			return ClienteMenuListener.EDITAR_CLIENTE.inicializarActivity(activity, args);
		}
	},
	REMOVER("Remover") {
		@Override
		public boolean efetuarAcao(ListagemClienteActivity activity, Map<ConstantesTransporte, String> args) {
			final Context context = activity.getApplicationContext();
			final Long idCliente = Long.valueOf(args.get(ConstantesTransporte.ID_CLIENTE));
			DeleteClienteAlert delete = new DeleteClienteAlert(context, activity, idCliente);
			delete.create().show();
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

	public abstract boolean efetuarAcao(ListagemClienteActivity activity, Map<ConstantesTransporte, String> args);
	
	public class DeleteClienteAlert extends AlertDialog.Builder {

		public DeleteClienteAlert(final Context context, final ListagemClienteActivity activity, Long idCliente) {
			super(activity);
			final ClienteDao clienteDao = new ClienteDao(context);
			final ClienteWrapper cliente = clienteDao.findById(idCliente);
			this.setTitle(R.string.excluir);
			this.setMessage(activity.getResources().getString(R.string.excluir_message) + cliente.getNomeCompleto());
			this.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					clienteDao.removeCliente(cliente);
					String search = activity.atualizaIntent();
					activity.atualizaListagem(search);
				}

			});
			this.setNeutralButton("NO", null);

		}
	}
}
