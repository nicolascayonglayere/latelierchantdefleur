import { SafeUrl } from '@angular/platform-browser';

export class ImageComposition {
  id: number;
  nom: string;
  description: string;
  content: string | ArrayBuffer;
  imgDisplay: SafeUrl;
  idComposition: number;
}
