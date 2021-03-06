package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;

public class RendererStandartImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererStandartImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMessage(String message) {
        System.out.println(preProcessor.preProcessMessage(message));
    }
}
