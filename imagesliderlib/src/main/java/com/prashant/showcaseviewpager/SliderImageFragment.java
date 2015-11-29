package com.prashant.showcaseviewpager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SliderImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SliderImageFragment extends Fragment {


    private static final String ARG_IMAGE_URL = "com.prashant.showcaseviewpager.SliderImageAdapter.imageUrl";
    private static final String ARG_OPEN_FULLSCREEN = "com.prashant.showcaseviewpager.SliderImageAdapter.openFullScreen";
    private static final String ARG_IMAGE_POSITION = "com.prashant.showcaseviewpager.SliderImageAdapter.imagePosition";
    private ArrayList<String> imageUrls;
    private int pos;
    private boolean openFullscreenActivity=false;

    public SliderImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageUrl Url of the Image to be loaded.
     * @return A new instance of fragment SliderImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SliderImageFragment newInstance(ArrayList<String> imageUrl,int pos,boolean openFullscreenActivity) {
        SliderImageFragment fragment = new SliderImageFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_IMAGE_URL, imageUrl);
        args.putInt(ARG_IMAGE_POSITION, pos);
        args.putBoolean(ARG_OPEN_FULLSCREEN,openFullscreenActivity);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnPaletteGeneratedListener(PaletteGenerator onPaletteGeneratedListener){
        paletteGenerator = onPaletteGeneratedListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrls = getArguments().getStringArrayList(ARG_IMAGE_URL);
            pos = getArguments().getInt(ARG_IMAGE_POSITION,0);
            openFullscreenActivity = getArguments().getBoolean(ARG_OPEN_FULLSCREEN,false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_slider_image, container, false);
        final SubsamplingScaleImageView sliderIv = (SubsamplingScaleImageView) view.findViewById(R.id.slider_image);
        Glide.with(getContext())
                .load(imageUrls.get(pos))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(paletteGenerator!=null)
                        Palette.from(resource)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        paletteGenerator.onPaletteGenerated(palette,pos);
                                    }
                                });
                        sliderIv.setImage(ImageSource.bitmap(resource));
                    }
                });
        if(openFullscreenActivity)
        sliderIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ImageSliderActivity.createIntent(getActivity(), imageUrls, pos), ImageSliderActivity.RESULT_CODE);
                /*
                * TODO: Send Intent to fullscreen VP activity
                * Send List of Images,Position
                * viewPager.getCurrentItem()
                */
            }
        });

        return view;
    }

    PaletteGenerator paletteGenerator=null;

}
