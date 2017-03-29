package org.owasp.csrfguard.action;

import org.owasp.csrfguard.util.ClassesMapHolder;

/**
 * Created by alexander.serbin on 3/29/2017.
 */
public final class IActionClassesMapHolder extends ClassesMapHolder<IAction> {

    public final static IActionClassesMapHolder INSTANCE = new IActionClassesMapHolder();

    private IActionClassesMapHolder() {
        init(Empty.class, Error.class, Forward.class, Invalidate.class, Log.class, Redirect.class,
                RequestAttribute.class, Rotate.class, SessionAttribute.class);
    }

}

