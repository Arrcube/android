package dev.agustacandi.learn.pestsentry.ui.article.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.databinding.FragmentDetailArticleBinding
import dev.agustacandi.learn.pestsentry.utils.ext.gone

class DetailArticleFragment : BaseFragment<FragmentDetailArticleBinding>() {

    private var articleUrl: String? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailArticleBinding =
        FragmentDetailArticleBinding.inflate(inflater, container, false)

    override fun initIntent() {
        articleUrl = arguments?.getString("articleUrl")
    }

    override fun initUI() {
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initProcess() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressCircular.gone()
                }
            }
            loadUrl(articleUrl!!)
        }
    }

    override fun initObservers() {
    }

}