package com.ols.ruslan.neo;

/**
 *  Интерфейс для EJB
 */
public interface MediaTypeTransformerFacade {
    // Сервер передает информацию потоком байтов
    byte[] transform(byte[] content, String encoding) throws Exception;
}
