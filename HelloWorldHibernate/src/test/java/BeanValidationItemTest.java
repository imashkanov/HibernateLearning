import org.jpwh.model.simple.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BeanValidationItemTest {
  private static ValidatorFactory validatorFactory;
  private static Validator validator;

  @BeforeClass
  public static void setUp() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void closeAll() {
    validatorFactory.close();
  }

  @Test
  public void m01_itemValidationTest() {
    Item item = new Item();
    item.setName("Test");
    item.setAuctionEnd(new Date());
    Set<ConstraintViolation<Item>> violations = validator.validate(item);
    for (ConstraintViolation<Item> violation : violations) {
      String failedPropertyName = violation.getPropertyPath().iterator().next().getName();
      String validationMessage = violation.getMessage();
      System.out.printf("Validation error: property = [%s], message = [%s]\n", failedPropertyName, validationMessage);
    }
  }
}
