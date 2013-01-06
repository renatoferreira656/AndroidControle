package app.controleprocessoslegais.listener;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import app.controleprocessoslegais.activitys.IncluirAlterarClienteActivity;
import app.controleprocessoslegais.activitys.ListagemClienteActivity;
import app.controleprocessoslegais.activitys.VisualizarClienteActivity;
import app.controleprocessoslegais.contantes.ConstantesTransporte;

public enum ClienteMenuListener {
	INCLUIR_CLIENTE {
		@Override
		public boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> args) {
			Intent intent = new Intent(activity, IncluirAlterarClienteActivity.class);
			intent.putExtra(ConstantesTransporte.NOME_CLIENTE_FILTRO.name(),
					args.get(ConstantesTransporte.NOME_CLIENTE_FILTRO));
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

	public abstract boolean inicializarActivity(Activity activity, Map<ConstantesTransporte, String> arg);
}
