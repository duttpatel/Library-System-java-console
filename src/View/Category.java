package View;

public class Category {
	private String categorId;
	
	private String categoryName;
	
	private static int categoryCount = 101;

	
	public Category(String categoryName) {
		super();
		this.categorId = autoIncrementId();
		this.categoryName = categoryName;
	}
	
	public String autoIncrementId(){
		String tempCategoryCount = "C" + categoryCount;
		categoryCount += 1;
        return tempCategoryCount;
	}
	
	public String getCategorId() {
		return categorId;
	}

	public void setCategorId(String categorId) {
		this.categorId = categorId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String toString(){
	    return this.categorId + " " + this.categoryName;
	}
}
