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

import static org.junit.Assert.assertEquals;

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
    item.setName("Some Item");
    item.setAuctionEnd(new Date());

    Set<ConstraintViolation<Item>> violations = validator.validate(item);
    assertEquals(1, violations.size());
    ConstraintViolation<Item> violation = violations.iterator().next();
    String failedPropertyName = violation.getPropertyPath().iterator().next().getName();
    assertEquals("auctionEnd", failedPropertyName);
    assertEquals("must be in future", violation.getMessage());
  }

}
