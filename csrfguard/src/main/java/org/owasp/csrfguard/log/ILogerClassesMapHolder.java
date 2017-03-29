package org.owasp.csrfguard.log;

import org.owasp.csrfguard.util.ClassesMapHolder;

/**
 * Created by alexander.serbin on 3/29/2017.
 */

public final class ILogerClassesMapHolder extends ClassesMapHolder<ILogger> {

    public final static ILogerClassesMapHolder INSTANCE = new ILogerClassesMapHolder();

    private ILogerClassesMapHolder() {
        init(ConsoleLogger.class, JavaLogger.class);
    }

}
