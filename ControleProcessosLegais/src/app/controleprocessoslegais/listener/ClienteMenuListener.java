package app.controleprocessoslegais.listener;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import app.controleprocessoslegais.R;
import app.controleprocessoslegais.activitys.IncluirAlterarClienteActivity;
import app.controleprocessoslegais.activitys.ListagemClienteActivity;
import app.controleprocessoslegais.activitys.VisualizarClienteActivity;
import app.controleprocessoslegais.contantes.ConstantesTransporte;
import app.controleprocessoslegais.dao.ClienteDao;
import app.controleprocessoslegais.wrappers.ClienteWrapper;

public enum ClienteMenuListener {
	INCLUIR_CLIENTE {
		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			Intent intent = new Intent(activity, IncluirAlterarClienteActivity.class);
			intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
					args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
			intent.putExtra(ConstantesTransporte.TELA_RETORNO.name(), this.getTelaRetorno());
			activity.startActivity(intent);
			activity.finish();
			return true;
		}
	},
	EDITAR_CLIENTE {

		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			Intent intent = new Intent(activity, IncluirAlterarClienteActivity.class);
			intent.putExtra(ConstantesTransporte.ID_CLIENTE.name(), args.get(ConstantesTransporte.ID_CLIENTE));
			intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
					args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
			intent.putExtra(ConstantesTransporte.TELA_RETORNO.name(), this.getTelaRetorno());
			activity.startActivity(intent);
			activity.finish();
			return true;
		}
	},
	LISTAGEM_CLIENTE {
		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			Intent intent = new Intent(activity, ListagemClienteActivity.class);
			intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
					args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
			activity.startActivity(intent);
			activity.finish();
			return true;
		}
	},
	EXCLUIR_CLIENTE {
		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			final Context context = activity.getApplicationContext();
			final Long idCliente = Long.valueOf(args.get(ConstantesTransporte.ID_CLIENTE));
			DeleteClienteAlert delete = new DeleteClienteAlert(context, activity, idCliente, args);
			delete.create().show();
			return false;
		}
	},
	VISUALIZAR_CLIENTE {
		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			Intent intent = new Intent(activity, VisualizarClienteActivity.class);
			intent.putExtra(ConstantesTransporte.ID_CLIENTE.name(), args.get(ConstantesTransporte.ID_CLIENTE));
			intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
					args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
			activity.startActivity(intent);
			activity.finish();
			return true;
		}
	};
	private ClienteMenuListener telaRetorno;

	public abstract boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> arg);

	public ClienteMenuListener getTelaRetorno() {
		return telaRetorno;
	}

	public void setTelaRetorno(ClienteMenuListener telaRetorno) {
		this.telaRetorno = telaRetorno;
	}

	public class DeleteClienteAlert extends AlertDialog.Builder {

		public DeleteClienteAlert(final Context context, final Activity activity, Long idCliente,
				final Map<ConstantesTransporte, String> args) {
			super(activity);
			final ClienteDao clienteDao = new ClienteDao(context);
			final ClienteWrapper cliente = clienteDao.findById(idCliente);
			this.setTitle(R.string.excluir);
			this.setMessage(activity.getResources().getString(R.string.excluir_message) + cliente.getNomeCompleto());
			this.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					clienteDao.removeCliente(cliente);
					Intent intent = new Intent(activity, ListagemClienteActivity.class);
					intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
							args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
					activity.startActivity(intent);
					activity.finish();
				}

			});
			this.setNeutralButton("NO", null);

		}
	}
}
