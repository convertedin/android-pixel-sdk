package convertedin.pixel.pixelsdk.viewmodel

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.Scheduler


abstract class BaseViewModel  {

    private val compositeDisposable = CompositeDisposable()

    fun <T> subscribe(
        Single: Single<T>,
        success: Consumer<T>,
        error: Consumer<Throwable>,
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeOnMainThread: Boolean = true
    ) {

        val observerScheduler =
            if (observeOnMainThread) AndroidSchedulers.mainThread()
            else subscribeScheduler

        compositeDisposable.add(
            Single
                .subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
                .subscribe(success, error)
        )
    }

    fun <T> subscribe(
        Single: Single<T>,
        success: Consumer<T>,
        error: Consumer<Throwable> = Consumer {},
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeOnMainThread: Boolean = true,
        showLoading: Boolean = true
    ) {

        val observerScheduler =
            if (observeOnMainThread) AndroidSchedulers.mainThread()
            else subscribeScheduler

        compositeDisposable.add(Single
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .compose { single ->
                composeSingle<T>(single, showLoading)
            }
            .subscribe(success, error))
    }

    private fun <T> composeSingle(single: Single<T>, showLoading: Boolean = true): Single<T> {
        return single
            .doOnError {
                Log.d("doOnError",it.message.toString())
              //  handleError(it)
            }
            .doOnSubscribe {
                Log.d("doOnSubscribe",(showLoading).toString())
               // loading.postValue(showLoading)
            }
            .doAfterTerminate {
                Log.d("doAfterTerminate","false")
               // loading.postValue(false)
            }
    }

    fun clearSubscription() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.clear()
    }


}