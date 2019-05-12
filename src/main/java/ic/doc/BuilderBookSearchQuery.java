package ic.doc;

public class BuilderBookSearchQuery {

  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;

  private BuilderBookSearchQuery() {}

  public static BuilderBookSearchQuery aBook() {
    return new BuilderBookSearchQuery();
  }

  public BuilderBookSearchQuery withName1(String name1) {
    this.name1 = name1;
    return this;
  }

  public BuilderBookSearchQuery withName2(String name2) {
    this.name2 = name2;
    return this;
  }

  public BuilderBookSearchQuery withTitle(String title) {
    this.title = title;
    return this;
  }

  public BuilderBookSearchQuery withDate1(Integer date1) {
    this.date1 = date1;
    return this;
  }

  public BuilderBookSearchQuery withDate2(Integer date2) {
    this.date2 = date2;
    return this;
  }

  public BookSearchQuery build() {
    BookSearchQuery instance = new BookSearchQuery(name1, name2, title, date1, date2);
    return instance;
  }

}
