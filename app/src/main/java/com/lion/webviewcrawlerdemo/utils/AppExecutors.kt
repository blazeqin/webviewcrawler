
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun runOnIOThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}