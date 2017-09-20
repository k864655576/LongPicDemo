package com.kwz.bottomdialoglib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 项目名称:LongPicDemo
 * 类描述:
 * 创建人:KWZ
 * 创建时间:2017/9/20 11:41
 */

public class BottomDialog extends BottomSheetDialog {
    private Context context;
    private TextView btnTakingPictures;
    private TextView btnLocalPhotoAlbum;
    private LinearLayout btnShortVideo;
    private TextView btnCancel;
    private LinearLayout llContainer;
    private BottomSheetBehavior<LinearLayout> behavior;
    private RelativeLayout touchOutSide;

    public BottomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        initView();
    }

    /**
     * 显示对话框
     *
     * @param isAll         是否显示全部
     * @param onAddListener 监听
     */
    public void showAddDialog(boolean outSide, boolean isAll, final OnAddListener onAddListener) {
        if (isAll) {
            btnShortVideo.setVisibility(View.VISIBLE);
        } else {
            btnShortVideo.setVisibility(View.GONE);
        }
        btnTakingPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddListener != null) {
                    onAddListener.onTakingPictures();
                }
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        btnLocalPhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddListener != null) {
                    onAddListener.onLocalPhotoAlbum();
                }
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        btnShortVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddListener != null) {
                    onAddListener.onShortVideo();
                }
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        touchOutSide.setEnabled(outSide);
        setCancelable(false);
        show();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.common_show_photo_dialog, null);
        touchOutSide = (RelativeLayout) view.findViewById(R.id.touch_outside);
        llContainer = (LinearLayout) view.findViewById(R.id.ll_container);
        btnTakingPictures = (TextView) view.findViewById(R.id.btnTakingPictures);
        btnLocalPhotoAlbum = (TextView) view.findViewById(R.id.btnLocalPhotoAlbum);
        btnShortVideo = (LinearLayout) view.findViewById(R.id.btnShortVideo);
        btnCancel = (TextView) view.findViewById(R.id.btnCancel);
        behavior = BottomSheetBehavior.from(llContainer);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        touchOutSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public interface OnAddListener {

        void onTakingPictures();

        void onLocalPhotoAlbum();

        void onShortVideo();
    }
}
