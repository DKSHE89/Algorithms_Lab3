package Lab_03;

import java.util.Iterator;

public abstract class  AbstractST<Key extends Comparable, Val>
                       implements Iterable<Key>
{   
    // abstract methods
    abstract public Boolean       isEmpty();
    abstract public void          put    (Key key, Val val);
    abstract public Val           get    (Key key);
    abstract public void          delete (Key key);
    abstract public Iterator<Key> iterator();   

    
    public Boolean contains(Key key) {
	return (get(key)!=null);
    }
    
}