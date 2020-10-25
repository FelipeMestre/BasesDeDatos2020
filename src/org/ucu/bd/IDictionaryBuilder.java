package org.ucu.bd;

import java.util.HashMap;

public interface IDictionaryBuilder {

    HashMap<String, Object> buildDictionary(String filePath);
}
