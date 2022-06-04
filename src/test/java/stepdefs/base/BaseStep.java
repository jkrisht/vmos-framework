package stepdefs.base;

import com.vmos.utils.VmosDriver;
import org.apache.log4j.Logger;

public class BaseStep {
    final Logger logger = Logger.getLogger(this.getClass());
    public VmosDriver driver;
}
