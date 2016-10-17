package com.wmz.demo_w1;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.color.CircleView;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.afollestad.materialdialogs.folderselector.FileChooserDialog;
import com.afollestad.materialdialogs.folderselector.FolderChooserDialog;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.internal.ThemeSingleton;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.wmz.demo_w1.base.BaseActivity;

import java.io.File;

import butterknife.OnClick;

public class MaterialDialogActivity extends BaseActivity implements
        FolderChooserDialog.FolderCallback, FileChooserDialog.FileCallback, ColorChooserDialog.ColorCallback {

    private Thread mThread;
    private Handler mHandler;

    // custom view dialog
    private EditText passwordInput;
    private View positiveAction;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_material_dialog;
    }

    @Override
    protected void initData() {
        super.initData();
        mHandler = new Handler();
    }

    @SuppressWarnings("ResourceAsColor")
    @OnClick(R.id.customView)
    public void showCustomView() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.googleWifi)
                .customView(R.layout.dialog_customview, true)
                .positiveText(R.string.connect)
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showToast("Password: " + passwordInput.getText().toString());
                    }
                }).build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        passwordInput = (EditText) dialog.getCustomView().findViewById(R.id.password);
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                positiveAction.setEnabled(s.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Toggling the initAdapter password CheckBox will mask or unmask the password input EditText
        CheckBox checkbox = (CheckBox) dialog.getCustomView().findViewById(R.id.showPassword);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
                passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
            }
        });

        int widgetColor = ThemeSingleton.get().widgetColor;
        MDTintHelper.setTint(checkbox,
                widgetColor == 0 ? ContextCompat.getColor(this, R.color.red) : widgetColor);

        MDTintHelper.setTint(passwordInput,
                widgetColor == 0 ? ContextCompat.getColor(this, R.color.red) : widgetColor);

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default
    }

    @OnClick(R.id.customView_datePicker)
    public void showCustomDatePicker() {
        new MaterialDialog.Builder(this)
                .title(R.string.date_picker)
                .customView(R.layout.dialog_datepicker, false)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .show();
    }


    @OnClick(R.id.progress1)
    public void showProgressDeterminateDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .contentGravity(GravityEnum.CENTER)
                .progress(false, 150, true)
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (mThread != null)
                            mThread.interrupt();
                    }
                })
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        final MaterialDialog dialog = (MaterialDialog) dialogInterface;
                        startThread(new Runnable() {
                            @Override
                            public void run() {
                                while (dialog.getCurrentProgress() != dialog.getMaxProgress() &&
                                        !Thread.currentThread().isInterrupted()) {
                                    if (dialog.isCancelled())
                                        break;
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        break;
                                    }
                                    dialog.incrementProgress(1);
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mThread = null;
                                        dialog.setContent(getString(R.string.md_done_label));
                                    }
                                });

                            }
                        });
                    }
                }).show();
    }

    @OnClick(R.id.progress2)
    public void showProgressIndeterminateDialog() {
//        showIndeterminateProgressDialog(false);
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.layout_progress,true)
                .build();
        Window window = dialog.getWindow();
        dialog.getCustomView();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = 300;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        dialog.show();
    }

    @OnClick(R.id.progress3)
    public void showProgressHorizontalIndeterminateDialog() {
        showIndeterminateProgressDialog(true);
    }

    private void showIndeterminateProgressDialog(boolean horizontal) {
        new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .show();
    }

    @OnClick(R.id.themed)
    public void showThemed() {
        new MaterialDialog.Builder(this)
                .title(R.string.useGoogleLocationServices)
                .content(R.string.useGoogleLocationServicesPrompt)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .positiveColorRes(R.color.red)
                .negativeColorRes(R.color.red)
                .titleGravity(GravityEnum.CENTER)
                .titleColorRes(R.color.red)
                .contentColorRes(android.R.color.white)
                .backgroundColorRes(R.color.material_blue_grey_800)
                .dividerColorRes(R.color.blue)
                .btnSelector(R.drawable.blue_button_background, DialogAction.POSITIVE)
                .positiveColor(Color.WHITE)
                .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                .theme(Theme.DARK)
                .show();
    }

    // color chooser dialog
    private int primaryPreselect;
    // UTILITY METHODS
    private int accentPreselect;

    @OnClick(R.id.colorChooser_customColorsNoSub)
    public void showColorChooserCustomColorsNoSub() {
        new ColorChooserDialog.Builder(this, R.string.color_palette)
                .titleSub(R.string.colors)
                .preselect(primaryPreselect)
                .customColors(R.array.custom_colors, null)
                .show();
    }

    // Receives callback from color chooser dialog
    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int color) {
        if (dialog.isAccentMode()) {
            accentPreselect = color;
            ThemeSingleton.get().positiveColor = DialogUtils.getActionTextStateList(this, color);
            ThemeSingleton.get().neutralColor = DialogUtils.getActionTextStateList(this, color);
            ThemeSingleton.get().negativeColor = DialogUtils.getActionTextStateList(this, color);
            ThemeSingleton.get().widgetColor = color;
        } else {
            primaryPreselect = color;
            if (getSupportActionBar() != null)
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(CircleView.shiftColorDown(color));
                getWindow().setNavigationBarColor(color);
            }
        }
    }

    private final static int STORAGE_PERMISSION_RC = 69;
    private int chooserDialog;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.file_chooser)
    public void showFileChooser() {
        chooserDialog = R.id.file_chooser;
        if (ActivityCompat.checkSelfPermission(MaterialDialogActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MaterialDialogActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_RC);
            return;
        }
        new FileChooserDialog.Builder(this)
                .show();
    }

    @Override
    public void onFileSelection(@NonNull FileChooserDialog dialog, @NonNull File file) {
        showToast(file.getAbsolutePath());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.folder_chooser)
    public void showFolderChooser() {
        chooserDialog = R.id.folder_chooser;
        if (ActivityCompat.checkSelfPermission(MaterialDialogActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MaterialDialogActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_RC);
            return;
        }
        new FolderChooserDialog.Builder(this)
                .chooseButton(R.string.md_choose_label)
                .allowNewFolder(true, 0)
                .show();
    }

    @Override
    public void onFolderSelection(@NonNull FolderChooserDialog dialog, @NonNull File folder) {
        showToast(folder.getAbsolutePath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_RC) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        findViewById(chooserDialog).performClick();
                    }
                }, 1000);
            } else {
                Toast.makeText(this, "The folder or file chooser will not work without permission to read external storage.", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void startThread(Runnable run) {
        if (mThread != null)
            mThread.interrupt();
        mThread = new Thread(run);
        mThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mThread != null && !mThread.isInterrupted() && mThread.isAlive())
            mThread.interrupt();
    }

}
