package io.sandbox.app.datasource

import androidx.paging.PageKeyedDataSource
import io.sandbox.app.base.actionLiveData
import io.sandbox.app.base.liveData
import io.sandbox.app.base.mutable
import io.sandbox.data.domain.CharactersUseCase
import io.sandbox.data.domain.Result
import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse

class CharacterDataSource(private val useCase: CharactersUseCase) : PageKeyedDataSource<Int, Character>() {

    val progress = liveData<Boolean>()
    val error = actionLiveData<Throwable>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) = useCase(
            inputParam = 1,
            consumer = resultConsumer {
                val characters = it.results
                if (params.placeholdersEnabled) callback.onResult(characters, 0, it.info.count, null, 2)
                else callback.onResult(characters, null, 2)
            })

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) = useCase(
            inputParam = params.key,
            consumer = resultConsumer { callback.onResult(it.results, params.key.inc()) })

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) = Unit

    override fun invalidate() {
        useCase.cancel()
        super.invalidate()
    }

    private fun resultConsumer(onSuccess: (CharactersResponse) -> Unit): (Result<CharactersResponse>) -> Unit = {
        progress.mutable.postValue(it is Result.Progress)
        when (it) {
            is Result.Success -> onSuccess(it.result)
            is Result.Error -> error.mutable.postValue(it.throwable)
        }
    }
}