import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class TischesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-tisches div table .btn-danger'));
  title = element.all(by.css('jhi-tisches div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class TischesUpdatePage {
  pageTitle = element(by.id('jhi-tisches-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  tischPlatteInput = element(by.id('field_tischPlatte'));
  tischBeinInput = element(by.id('field_tischBein'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setTischPlatteInput(tischPlatte) {
    await this.tischPlatteInput.sendKeys(tischPlatte);
  }

  async getTischPlatteInput() {
    return await this.tischPlatteInput.getAttribute('value');
  }

  async setTischBeinInput(tischBein) {
    await this.tischBeinInput.sendKeys(tischBein);
  }

  async getTischBeinInput() {
    return await this.tischBeinInput.getAttribute('value');
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class TischesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-tisches-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-tisches'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
