package Game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yentl on 25-Dec-16.
 */
public final class ActivityManager {
    private static ActivityManager _activityManager = null;

    private final ExecutorService _executorService;

    public static ActivityManager getInstance() {
        if (_activityManager == null) {
            _activityManager = new ActivityManager();
        }
        return _activityManager;
    }

    private ActivityManager() {
        _executorService = Executors.newCachedThreadPool();
    }

    public void submit(final Runnable task) {
        _executorService.submit(task);
    }
}
