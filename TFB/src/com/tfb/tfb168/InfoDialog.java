package com.tfb.tfb168;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDialog extends Dialog {

    public InfoDialog(Context context, int theme) {
        super(context, theme);
    }

    public InfoDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        Button mbuttonChePaiDi;
        EditText medittextChepaiHao;
        EditText meditTextFadongjiHao;
       ImageView mimageViewloading;
        private Context context;
        private String title;
        private String message;
        private boolean progressshow = false;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;


        private DialogInterface.OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         * 
         * @param title
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        
        public Builder setprogressshow(boolean show) {
            this.progressshow = show;
            return this;
        }
        
        /**
         * Set the Dialog message from resource
         * 
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         * 
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         * 
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog. If a message is set, the
         * contentView is not added to the Dialog...
         * 
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public InfoDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final InfoDialog dialog = new InfoDialog(context
                    , R.style.Dialog
                    );
            View layout = inflater.inflate(R.layout.dialog_info,
                    null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            // ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
//            if (positiveButtonText != null) {
//                ((Button) layout.findViewById(R.id.positiveButton))
//                        .setText(positiveButtonText);
//                if (positiveButtonClickListener != null) {
//                    ((Button) layout.findViewById(R.id.positiveButton))
//                            .setOnClickListener(new View.OnClickListener() {
//                                public void onClick(View v) {
//                                    positiveButtonClickListener.onClick(dialog,
//                                            DialogInterface.BUTTON_POSITIVE);
//                                }
//                            });
//                }
//            } else {
                // if no confirm button just set the visibility to GONE
//                layout.findViewById(R.id.positiveButton).setVisibility(
//                        View.GONE);
//            }
            // set the cancel button
//            if (negativeButtonText != null) {
//                ((Button) layout.findViewById(R.id.negativeButton))
//                        .setText(negativeButtonText);
//                if (negativeButtonClickListener != null) {
//                    ((Button) layout.findViewById(R.id.negativeButton))
//                            .setOnClickListener(new View.OnClickListener() {
//                                public void onClick(View v) {
//                                    negativeButtonClickListener.onClick(dialog,
//                                            DialogInterface.BUTTON_NEGATIVE);
//                                }
//                            });
//                }
//            } else {
//                // if no confirm button just set the visibility to GONE
//                layout.findViewById(R.id.negativeButton).setVisibility(
//                        View.GONE);
//            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            }
           
            mimageViewloading= ((ImageView) layout.findViewById(R.id.imageViewloading));
            mimageViewloading.setVisibility(progressshow ? View.VISIBLE : View.GONE);
//            if(progressshow) mHandler.sendEmptyMessageDelayed(RotuteBitmap, 500);
           
            
//            mimageViewloading.get
//             if (message != null) {
//             ((TextView) layout.findViewById(R.id.message)).setText(message);
//             } else if (contentView != null) {
//             // if no message set
//             // add the contentView to the dialog body
//             ((LinearLayout) layout.findViewById(R.id.content))
//             .removeAllViews();
//             ((LinearLayout) layout.findViewById(R.id.content)).addView(
//             contentView, new LayoutParams(
//             LayoutParams.WRAP_CONTENT,
//             LayoutParams.WRAP_CONTENT));
//             }
//            mbuttonChePaiDi =((Button) layout.findViewById(R.id.buttonChePaiDi));
//            mbuttonChePaiDi.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                   
//                }
//                
//            });
            

//            medittextChepaiHao =((EditText) layout.findViewById(R.id.edittextChepaiHao));
//            meditTextFadongjiHao =((EditText) layout.findViewById(R.id.editTextFadongjiHao));
//            if(mChepaiHao != null) {
//                medittextChepaiHao.setText(mChepaiHao);
//            }
//            if(mFadongjiHao != null) {
//                meditTextFadongjiHao.setText(mFadongjiHao);
//            }
            dialog.setContentView(layout);
            return dialog;
        }

        
        static final int RotuteBitmap = 999;
        Handler mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {

                switch (msg.what) {

                case RotuteBitmap:
                    Bitmap bitmap = ((BitmapDrawable) mimageViewloading.getBackground()).getBitmap();
                    Matrix matrix = new Matrix();
                    matrix.setRotate(30,(float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2); //设置旋转
                    Bitmap bitmapcute = Bitmap.createBitmap(bitmap, 0, 0, 
                            bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    
//                    mimageViewloading.getBackground().
                    mimageViewloading.setBackground(new BitmapDrawable(bitmapcute));
                    bitmapcute = null;
                    if(bitmap!=bitmapcute)
                    {
                        bitmap.recycle();
                    }
                    mHandler.sendEmptyMessageDelayed(RotuteBitmap, 100);
                    break;
                }
                ;

            };

        };
//        private String mChepaiHao = null;
//        private String mFadongjiHao = null;
//        public Builder setCarInfo(String ChepaiHao,String FadongjiHao) {
//            mChepaiHao = ChepaiHao;mFadongjiHao = FadongjiHao;
//            return this;
//        }
    }
    
    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees,
                    (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(
                        b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
                    b.recycle();  //Android开发网再次提示Bitmap操作完应该显示的释放
                    b = b2;
                }
            } catch (OutOfMemoryError ex) {
                // Android123建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.
            }
        }
        return b;
    }


}
