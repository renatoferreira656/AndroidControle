package app.controleprocessoslegais.activitys.sectionlistview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Item> {

	private List<Item> items;
	private LayoutInflater layoutInflater;
	private int listItemSection;
	private int listItemSectionText;
	private int listItemEntry;
	private int listItemEntryTitle;

	/**
	 * 
	 * @param context
	 *            = Contexto da aplicacao
	 * @param items
	 *            = Itens contidos na listagem
	 * @param listItemEntry
	 *            = Id do Layout de Entrada
	 * @param listItemSection
	 *            = Id do layout da secao
	 * @param listItemSectionText
	 *            = Id da Secao de Texto
	 * @param listItemEntryTitle
	 *            = Id do Texto
	 * @param listItemEntrySummary
	 *            = Id do Sumario do Texto
	 */
	public EntryAdapter(Context context, List<Item> items, int listItemEntry, int listItemSection,
			int listItemSectionText, int listItemEntryTitle) {
		super(context, 0, items);
		this.items = items;
		this.listItemSection = listItemSection;
		this.listItemSectionText = listItemSectionText;
		this.listItemEntryTitle = listItemEntryTitle;
		this.listItemEntry = listItemEntry;
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final Item item = items.get(position);
		if (item != null) {
			if (item.isSection()) {
				view = defineSectionItem(item);
			} else {
				view = defineEntryItem(item);
			}
		}
		return view;
	}

	private View defineSectionItem(final Item item) {
		View view;
		SectionItem sectionItem = SectionItem.class.cast(item);
		view = this.layoutInflater.inflate(this.listItemSection, null);

		view.setOnClickListener(null);
		view.setOnLongClickListener(null);
		view.setLongClickable(false);

		final TextView sectionView = (TextView) view.findViewById(this.listItemSectionText);
		sectionView.setText(sectionItem.getTitle());
		return view;
	}

	private View defineEntryItem(final Item item) {
		View view;
		EntryItem entryItem = EntryItem.class.cast(item);
		view = this.layoutInflater.inflate(this.listItemEntry, null);
		
		final TextView title = (TextView) view.findViewById(this.listItemEntryTitle);
		if (title != null) {
			title.setText(entryItem.getTitle());
		}
		return view;
	}

}
