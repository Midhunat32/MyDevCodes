package com.wisilica.androdev.ui.activity.Exoplayer

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import android.widget.Toast
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.wisilica.androdev.R
import kotlinx.android.synthetic.main.activity_exoplayer.*

class Exoplayer : AppCompatActivity(), View.OnClickListener {

    private lateinit var videoView: PlayerView
    private lateinit var exoPlayer:ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var stateBuilder: PlaybackStateCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)

        initExoPlayer()
        registerListener()
    }

    private fun registerListener() {
        btnPlay.setOnClickListener(this)
    }

    private fun initExoPlayer() {
        val trackSelector=DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderFactory = DefaultRenderersFactory(this)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderFactory, trackSelector, loadControl)
    }

    override fun onClick(v: View?) {
        when(v){
            btnPlay->{
                Toast.makeText(this,"Video started playing",Toast.LENGTH_LONG).show()
                playVideo()
            }
        }
    }

    private fun playVideo() {

      //  setMediPlayer()
        playerView.player = exoPlayer
        val videoUrl = "https://www.youtube.com/watch?v=JbY8DM8c-h0"
        val userAgent = "com.wisilica.androdev"
        val mediaSource = ExtractorMediaSource
            .Factory(DefaultDataSourceFactory(this, userAgent))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(videoUrl))

        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady =true

    }

    private fun setMediPlayer() {

        mediaSession = MediaSessionCompat(this, "vhh")
        mediaSession.setFlags(
            MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                    MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )
        mediaSession.setMediaButtonReceiver(null)

        stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE or
                        PlaybackStateCompat.ACTION_FAST_FORWARD or
                        PlaybackStateCompat.ACTION_REWIND
            )

        mediaSession.setPlaybackState(stateBuilder.build())

        mediaSession.setCallback(SessionCallback())

        mediaSession.isActive = true
    }

    private inner class SessionCallback : MediaSessionCompat.Callback() {

        private val SEEK_WINDOW_MILLIS = 10000

        override fun onPlay() {
            exoPlayer.playWhenReady = true
        }

        override fun onPause() {
            exoPlayer.playWhenReady = false
        }

        override fun onRewind() {
            exoPlayer.seekTo(exoPlayer.currentPosition - SEEK_WINDOW_MILLIS)
        }

        override fun onFastForward() {
            exoPlayer.seekTo(exoPlayer.currentPosition + SEEK_WINDOW_MILLIS)
        }
    }


    private fun stopExoPlayer(){
        exoPlayer.stop()
        exoPlayer.release()
    }

    override fun onStop() {
        super.onStop()
        stopExoPlayer()
    }

}
