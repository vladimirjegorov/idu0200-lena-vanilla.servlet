package ee.ttu.idu0200.validator;

import ee.ttu.idu0200.form.TeamForm;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class TeamFormValidator {

  public Map<String, String> validate(TeamForm teamForm) {
    Map<String, String> bindingResult = new HashMap<>();

    validateMembersAmount(teamForm.getMembersAmount(), bindingResult);
    validateTitle(teamForm.getTitle(), bindingResult);
    validateDescription(teamForm.getDescription(), bindingResult);

    return bindingResult;
  }

  void validateMembersAmount(String membersAmountString, Map<String, String> bindingResult) {
    if (isBlank(membersAmountString)) {
      bindingResult.put("team.membersAmount", "team.membersAmount.empty");
    } else {
      try {
        int membersAmount = parseInt(membersAmountString);
        if (membersAmount < 0) {
          bindingResult.put("team.membersAmount",  "team.membersAmount.tooLow");
        }
        if (membersAmount > 20) {
          bindingResult.put("team.membersAmount",  "team.membersAmount.tooMuch");
        }
      } catch (NumberFormatException e) {
        bindingResult.put("team.membersAmount", "team.membersAmount.invalidFormat");
      }
    }
  }

  void validateTitle(String title, Map<String, String> bindingResult) {
    if (isEmpty(title)) {
      bindingResult.put("team.title", "team.title.empty");
    } else {
      if (title.length() > 100) {
        bindingResult.put("team.title", "team.title.tooLong");
      }
    }
  }

  void validateDescription(String description, Map<String, String> bindingResult) {
    if (isEmpty(description)) {
      bindingResult.put("team.description", "team.description.empty");
    } else {
      if (description.length() > 500) {
        bindingResult.put("team.description", "team.description.tooLong");
      }
    }
  }
}
