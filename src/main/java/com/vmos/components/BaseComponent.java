package com.vmos.components;

import com.vmos.utils.VmosDriver;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public abstract class BaseComponent implements IComponent {
    protected final Logger logger = Logger.getLogger(this.getClass());
    protected VmosDriver driver;
    protected ResourceBundle bundle;
}
