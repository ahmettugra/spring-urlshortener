package common.libs.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationManager {
    private static Map<String, Properties> props = new HashMap<String, Properties>();
    private static Log log = LogFactory.getLog(ConfigurationManager.class);

    private static String hostname;
    private static String environment;
    private static String configDirectory = null;

    public static String getEnvironment() {
        return environment;
    }

    static {
        // getting the hostname
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException e) {
            log.fatal("Unable to get hostname, can't continue. Sorry....", e);
        }

        log.info("Hostname was found as " + hostname);

        // getting environment
        environment = System.getenv("ENVIRONMENT");
        if (environment == "" || environment == null) {
            environment = "PROD";
        }
        log.info("Environment was found as " + environment);

        //setConfigDirectory("/etc/configs/");
    }

    private static Properties getProperties(String propFile) {
        Properties prop = props.get(propFile);
        if (prop == null) {
            log.info("Property file: " + propFile + "is not Cached");
            // getting local properties
            InputStream is = null;
            prop = new Properties();

            try {
                if (configDirectory == null) {
                    is = ConfigurationManager.class.getResourceAsStream("/common/conf/" + propFile + ".properties");
                } else {
                    is = new FileInputStream(configDirectory + propFile + ".properties");
                }

                prop.load(is);
                props.put(propFile, prop);
                is.close();
            } catch (Exception e) {
                log.fatal("Unable to load " + propFile + " properties, can't continue. Sorry....", e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {}
                }
            }
        }
        return prop;
    }

    /**
     *
     * @param key
     * @return
     */
    private static String getProperty(String file, String key) {
        log.info("Getting property: " + file + "::" + key);
        Properties prop = getProperties(file);
        String result = null;

        // Find by environment
        if (prop != null) {
            log.info("Getting value for key: " + environment + "." + key);
            result = prop.getProperty(environment + "." + key);

            // Return default value
            if (result == null) {
                result = prop.getProperty(key);
            }
        }

        return result;
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getString(String file, String key) {
        return getProperty(file, key);
    }

    public static String getString(String file, String key, String default_value) {
        String v = getProperty(file, key);
        if (v != null)
            return v;
        return default_value;
    }

    /**
     *
     * @param key
     * @return
     */
    public static Boolean getBoolean(String file, String key) {
        String str = getString(file, key);
        Boolean result = null;
        if (str != null) {
            result = Boolean.valueOf(str);
        }
        return result;
    }

    /**
     *
     * @param key
     * @return
     */
    public static Integer getInteger(String file, String key) {
        String str = getString(file, key);
        Integer result = null;
        if (str != null) {
            result = Integer.valueOf(str);
        }
        return result;
    }

    /**
     *
     * @param key
     * @param seperator
     * @return
     */
    public static String[] getList(String file, String key, String seperator) {
        String val = getString(file, key);
        String[] vals = null;
        if (val != null) {
            String[] tmp = val.split(seperator);
            if (tmp != null) {
                vals = new String[tmp.length];
                for (int i = 0; i < tmp.length; i++) {
                    vals[i] = tmp[i].trim();
                }
            }
        }
        return vals;
    }

    /**
     * Sets configuration directory
     *
     * @param dir
     */
    public static void setConfigDirectory(String dir) {
        if (dir != null && !dir.equals("")) {
            if (!dir.endsWith("/")) {
                dir += "/";
            }
            configDirectory = dir;
            log.info("Setting config directory : " + configDirectory);
        }
    }
}
