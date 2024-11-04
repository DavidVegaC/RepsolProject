package com.repsol.core_domain.common.use_case

interface SimpleUseCase {

    interface ParamsAndResult<Parameters, Result>: SimpleUseCase {
        suspend operator fun invoke(params: Parameters): Result
    }

    interface ParamsAndOptionalResult<Parameters, Result>: SimpleUseCase {
        suspend operator fun invoke(params: Parameters): Result?
    }

    interface OptionalParamsAndResult<Parameters, Result>: SimpleUseCase {
        suspend operator fun invoke(params: Parameters? = null): Result
    }

    interface OnlyParams<Parameters>: SimpleUseCase {
        suspend operator fun invoke(params: Parameters)
    }

    interface OnlyResult<Result>: SimpleUseCase {
        suspend operator fun invoke(): Result
    }

    interface Empty: SimpleUseCase {
        suspend operator fun invoke()
    }

}