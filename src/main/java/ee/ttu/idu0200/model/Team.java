package ee.ttu.idu0200.model;

public class Team {

  private long id;
  private String title;
  private int membersAmount;
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getMembersAmount() {
    return membersAmount;
  }

  public void setMembersAmount(int membersAmount) {
    this.membersAmount = membersAmount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
