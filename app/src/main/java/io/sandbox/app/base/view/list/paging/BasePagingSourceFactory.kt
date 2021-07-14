package io.sandbox.app.base.view.list.paging

import androidx.paging.PagingSource

abstract class BasePagingSourceFactory<K : Any, V : Any, P>(
    defaultParams: P
) : () -> PagingSource<K, V> {

    private var dataSource: PagingSource<K, V>? = null
    var params: P = defaultParams
        set(value) {
            field = value
            dataSource?.invalidate()
        }

    final override fun invoke(): PagingSource<K, V> {
        val dataSource = createDataSource(params)
        this.dataSource = dataSource
        return dataSource
    }

    protected abstract fun createDataSource(parameters: P): PagingSource<K, V>
}