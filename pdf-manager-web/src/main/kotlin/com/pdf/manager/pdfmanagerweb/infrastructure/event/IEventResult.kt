package com.pdf.manager.pdfmanagerweb.infrastructure.event

/**
 * 表示一个带有结果的事件
 */
interface IEventResult<TResult : Any?> : IEvent {

    /**
     * 获取结果
     */
    var result: TResult
}