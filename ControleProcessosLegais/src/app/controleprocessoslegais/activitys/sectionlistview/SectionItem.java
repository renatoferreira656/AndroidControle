package app.controleprocessoslegais.activitys.sectionlistview;

public class SectionItem implements Item, Comparable<SectionItem> {

	private final String title;

	public SectionItem(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public boolean isSection() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionItem other = (SectionItem) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int compareTo(SectionItem other) {
		if (title == null) {
			if (other.title != null) {
				return -1;
			}
			return 0;
		} else {
			if (other.title == null) {
				return 1;
			} else {
				return this.title.compareTo(other.getTitle());
			}
		}
	}

}
