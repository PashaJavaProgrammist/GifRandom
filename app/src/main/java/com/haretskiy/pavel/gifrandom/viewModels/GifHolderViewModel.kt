package com.haretskiy.pavel.gifrandom.viewModels

import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


class GifHolderViewModel(imageLoader: ImageLoader, private val data: Data, imageView: ImageView) {

    val progress = ObservableBoolean(true)
    val url = ObservableField<String>(data.images?.original?.url ?: EMPTY_STRING)
    val progressBar = ObservableField<ObservableBoolean>(progress)
    val loader = ObservableField<ImageLoader>(imageLoader)
    val image = ObservableField<ImageView>(imageView)

    fun onItemClick(v: View) {

    }
}

@BindingAdapter("imageView", "imageUrl", "progressBar", "imageLoader")
fun ImageView.loadImage(imageView: ImageView, url: String, progress: ObservableBoolean, imageLoader: ImageLoader) {

    imageLoader.loadImage(imageView, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
            progress.set(true)
        }

        override fun onLoadFinished() {
            progress.set(false)
        }

        override fun onLoadFailed() {
            progress.set(false)
        }
    })
}
//
//[kapt] An exception occurred: android.databinding.tool.util.LoggedErrorException: Found data binding errors.
//****/ data binding error ****msg:Cannot find the setter for attribute 'tool:imageView' with
//parameter type android.databinding.ObservableField<android.widget.ImageView> on
//android.widget.ImageView. file:/home/pavel/StudioProjects/My/GifRandom/app/src/main/res/layout/item_holder.xml
//loc:28:30 - 28:47 ****\ data binding error ****
//
//at android.databinding.tool.processing.Scope.assertNoError(Scope.java:112)
//at android.databinding.annotationprocessor.ProcessDataBinding.doProcess(ProcessDataBinding.java:101)
//at android.databinding.annotationprocessor.ProcessDataBinding.process(ProcessDataBinding.java:65)
//at org.jetbrains.kotlin.kapt3.ProcessorWrapper.process(annotationProcessing.kt:131)
//at com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:794)
//at com.sun.tools.javac.processing.JavacProcessingEnvironment.access$200(JavacProcessingEnvironment.java:91)
//at com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors$ProcessorStateIterator.runContributingProcs(JavacProcessingEnvironment.java:627)
//at com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1033)
//at com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1198)
//at com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1170)
//at com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1068)
//at org.jetbrains.kotlin.kapt3.AnnotationProcessingKt.doAnnotationProcessing(annotationProcessing.kt:87)
//at org.jetbrains.kotlin.kapt3.AnnotationProcessingKt.doAnnotationProcessing$default(annotationProcessing.kt:45)
//at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.runAnnotationProcessing(Kapt3Extension.kt:257)
//at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.analysisCompleted(Kapt3Extension.kt:212)
//at org.jetbrains.kotlin.kapt3.ClasspathBasedKapt3Extension.analysisCompleted(Kapt3Extension.kt:95)
//at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM$analyzeFilesWithJavaIntegration$2.invoke(TopDownAnalyzerFacadeForJVM.kt:97)
//at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(TopDownAnalyzerFacadeForJVM.kt:107)
//at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration$default(TopDownAnalyzerFacadeForJVM.kt:84)
//at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:374)
//at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:64)
//at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:101)
//at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.analyze(KotlinToJVMBytecodeCompiler.kt:365)
//at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:130)
//at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:161)
//at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:63)
//at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.java:107)
//at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.java:51)
//at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:96)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl$compile$1$1$2.invoke(CompileServiceImpl.kt:405)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl$compile$1$1$2.invoke(CompileServiceImpl.kt:98)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl$doCompile$$inlined$ifAlive$lambda$2.invoke(CompileServiceImpl.kt:920)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl$doCompile$$inlined$ifAlive$lambda$2.invoke(CompileServiceImpl.kt:98)
//at org.jetbrains.kotlin.daemon.common.DummyProfiler.withMeasure(PerfUtils.kt:137)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl.checkedCompile(CompileServiceImpl.kt:950)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl.doCompile(CompileServiceImpl.kt:919)
//at org.jetbrains.kotlin.daemon.CompileServiceImpl.compile(CompileServiceImpl.kt:404)
//at sun.reflect.GeneratedMethodAccessor115.invoke(Unknown Source)
//at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//at java.lang.reflect.Method.invoke(Method.java:498)
//at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:346)
//at sun.rmi.transport.Transport$1.run(Transport.java:200)
//at sun.rmi.transport.Transport$1.run(Transport.java:197)
//at java.security.AccessController.doPrivileged(Native Method)
//at sun.rmi.transport.Transport.serviceCall(Transport.java:196)
//at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:568)
//at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:826)
//at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:683)
//at java.security.AccessController.doPrivileged(Native Method)
//at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:682)
//at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
//at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
//at java.lang.Thread.run(Thread.java:745)