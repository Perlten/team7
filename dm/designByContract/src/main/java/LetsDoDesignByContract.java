import static org.valid4j.Assertive.*;
import static org.valid4j.Validation.*;
import static org.hamcrest.Matchers.*;

public class LetsDoDesignByContract {
    private int x;


    public int divide(int a, int b) {
        // {P}
        require(a > 0, "a should be large then 0");
        require(b != 0, "b can not be 0");
        validate(a != b, otherwiseThrowing(IsLessThenBrightException.class));

        // C
        this.x = a / b;

        // {Q}
        ensure(this.x == a / b);
        return ensure(this.x, is(a / b));
    }

    public int getX() {
        return x;
    }
}

