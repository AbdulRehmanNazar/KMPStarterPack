package com.starter.app.data.config

/**
 * Provider of Server Base URL
 */
class DefaultNetworkConfig : NetworkConfig {
    override val baseUrl = "https://api.github.com/repos/ruby/ruby"
}